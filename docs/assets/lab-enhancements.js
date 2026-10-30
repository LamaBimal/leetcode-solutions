// Navigation, progress, presets, and structural views are independent of pure traces.
let restoringRoute=false;
const attempts=new Map();
function recordAnswer(index,correct){if(!attempts.has(index))attempts.set(index,correct);const right=[...attempts.values()].filter(Boolean).length;$('quizScore').textContent=`First attempts: ${right}/${attempts.size}. Missed: ${[...attempts].filter(([,ok])=>!ok).map(([i])=>i+1).join(', ')||'none'}.`;}
const score=document.createElement('p');score.id='quizScore';score.setAttribute('role','status');$('questionCounter').after(score);
function lessonKey(){return mode==='quiz'?'quiz':`${mode}/${selected.id}`;}
function readProgress(){try{const value=JSON.parse(localStorage.getItem('pattern-lab-progress-v1')||'{}');return value&&typeof value==='object'&&!Array.isArray(value)?value:{};}catch{return {};}}
function lessonChanged(){
 const key=lessonKey();if(!restoringRoute){const route='#lesson='+encodeURIComponent(key);if(location.hash!==route)history.pushState(null,'',route);}
 const done=readProgress()[key];$('complete-lesson').textContent=done?'Completed ✓ · mark incomplete':'Mark lesson complete';
 $('lesson-route').textContent=mode==='complexity'?'Foundations → count operations → simplify → analyze memory → compare designs.':mode==='algorithms'?'Practice · 10–15 min · Prerequisites: Big O, arrays, loops.':mode==='quiz'?'Apply · 10 min · Prerequisites: algorithm and design lessons.':'Intermediate · 10 min · Prerequisites: object contracts and composition.';
 $('share-status').textContent='';renderPresets();
}
function restoreRoute(){restoringRoute=true;try{const raw=location.hash==='#complexity'?'complexity/basics':location.hash.startsWith('#lesson=')?decodeURIComponent(location.hash.slice(8)):'algorithms/pair';const [group,id]=raw.split('/');if(!['algorithms','solid','design','quiz','complexity'].includes(group))return;setMode(group);const item=(group==='algorithms'?lessons:group==='complexity'?complexityLessons:concepts).find(c=>c.id===id&&(['algorithms','complexity'].includes(group)||c.group===group));if(item){selected=item;renderMenu();loadLesson();}lessonChanged();}catch{setMode('algorithms');}finally{restoringRoute=false;}}
window.addEventListener('popstate',restoreRoute);window.addEventListener('hashchange',()=>{if(location.hash.startsWith('#lesson=')||location.hash==='#complexity')restoreRoute();});
function renderPresets(){const root=$('presets');root.replaceChildren();if(mode!=='algorithms')return;const id=selected.id;const examples=[['Default',selected.input,selected.param]];
 if(!['dfs','matrix','heap'].includes(id))examples.push(['Empty / zero',id==='window'?'':id==='dp'?0:[],id==='cycle'?-1:selected.param]);
 if(['prefix','pair','stack','heap'].includes(id))examples.push(['Duplicates',[2,2,2],id==='heap'?2:4]);
 if(['prefix','pair','binary'].includes(id))examples.push(['No match',id==='binary'?[3,4,1,2]:[1,2,3],99]);
 if(id==='window')examples.push(['Repeated characters','aaaa']);
 if(id==='cycle')examples.push(['Single self-loop',[7],0],['No cycle',[1,2,3],-1]);
 if(id==='dp')examples.push(['Upper teaching bound',24]);
 if(['dfs','matrix'].includes(id))examples.push(['One cell',[[id==='dfs'?1:2]],id==='matrix'?2:undefined]);
 for(const [name,input,param]of examples){const b=document.createElement('button');b.type='button';b.textContent=name;b.onclick=()=>{$('input').value=JSON.stringify(input);if(param!==undefined)$('parameter').value=param;build();};root.append(b);}}
function enrichFrame(f){
 const outline=$('outline-step');outline.replaceChildren();selected.outline.split('\n').forEach((text,i)=>{const line=document.createElement('span');line.className='outline-line';if(i+1===f.outlineLine){line.classList.add('current-line');line.setAttribute('aria-current','step');}line.textContent=`${i+1}  ${text}`;outline.append(line);});
 const root=$('structure');root.replaceChildren();const s=f.state;
 function node(text){const el=document.createElement('span');el.className='node';el.textContent=text;return el;}
 if(s.nextIndices){const labels={head:s.head,previous:s.previous,current:s.current,slow:s.slow,fast:s.fast};for(let i=0;i<s.nextIndices.length;i++){const pointers=Object.entries(labels).filter(([,v])=>v===i).map(([k])=>k).join(', ');root.append(node(`Node ${i}${pointers?' ['+pointers+']':''} → ${s.nextIndices[i]===null?'∅':'node '+s.nextIndices[i]}`));}}
 if(s.queueIndices){root.append(node('FRONT → '+(s.queueIndices.join(' → ')||'empty')+' ← BACK'));}
 if(s.stackIndices){root.append(node('BOTTOM → '+(s.stackIndices.join(' → ')||'empty')+' ← TOP'));}
 if(s.heapLevelOrder){const heap=s.heapLevelOrder;const ul=document.createElement('ul');function branch(i){if(i>=heap.length)return null;const li=document.createElement('li');li.append(node(`index ${i}: ${heap[i]}`));const children=document.createElement('ul');for(const c of [2*i+1,2*i+2]){const child=branch(c);if(child)children.append(child);}li.append(children);return li;}const tree=branch(0);if(tree)ul.append(tree);root.append(ul);}
 if(selected.id==='backtrack'){
 const paths=new Map();for(const frame of frames.slice(0,position+1)){if(frame.state.path){const path=frame.state.path;paths.set(JSON.stringify(path),path);}}
 const tree=document.createElement('ul');function branch(path){const li=document.createElement('li');const label=node(path.length?'['+path.join(', ')+']':'root · []');if(JSON.stringify(path)===JSON.stringify(s.path))label.classList.add('active');li.append(label);const children=document.createElement('ul');for(const child of paths.values())if(child.length===path.length+1&&path.every((v,i)=>child[i]===v))children.append(branch(child));li.append(children);return li;}tree.append(branch([]));root.append(tree);
 }
}
const complete=$('complete-lesson');complete.onclick=()=>{const progress=readProgress(),key=lessonKey();progress[key]=!progress[key];try{localStorage.setItem('pattern-lab-progress-v1',JSON.stringify(progress));}catch{$('share-status').textContent='Progress storage is unavailable in this browser.';}restoringRoute=true;lessonChanged();restoringRoute=false;};
$('share-lesson').onclick=()=>{$('share-status').textContent=location.href;};
restoreRoute();
// Dynamic lesson content changes page height before the browser's initial anchor.
window.addEventListener('load',()=>{const target=location.hash==='#complexity'?$('title'):location.hash.startsWith('#lesson=')?$('title'):null;if(target)target.scrollIntoView({block:'start'});});
