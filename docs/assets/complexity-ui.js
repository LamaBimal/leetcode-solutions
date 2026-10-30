// Dedicated Big O module. Shared counters do not execute repository Java code.
let complexityStep=0;
function renderComplexityLesson(){
 complexityStep=0;
 $('complexity-adjustment').textContent='';$('worksheet-feedback').hidden=true;document.querySelectorAll('[data-worksheet]').forEach(field=>field.value='');
 $('complexity-basics').hidden=selected.id!=='basics';
 $('complexity-derivation').replaceChildren();
 const list=$('complexity-derivation');
 selected.steps.forEach(([heading,text],index)=>{const item=document.createElement('li');item.dataset.derivationStep=index;const strong=document.createElement('strong');strong.textContent=heading+'. ';item.append(strong,document.createTextNode(text));list.append(item);});
 $('complexity-code').textContent=selected.code;
 $('complexity-trap').textContent=selected.trap;
 $('complexity-result').textContent=selected.cost;
 $('complexity-n').value=8;$('complexity-k').value=3;
 $('complexity-k-controls').hidden=selected.kind!=='heap';
 const links=$('complexity-links');links.replaceChildren();
 if(selected.source){const source=document.createElement('a');source.href='../src/main/java/com/learning/'+selected.source+'.java';source.textContent='Inspect the repository implementation ↗';links.append(source);}
 if(selected.pattern){const pattern=document.createElement('a');pattern.href='#lesson='+encodeURIComponent('algorithms/'+selected.pattern);pattern.textContent='Open the corresponding algorithm simulation';links.append(document.createTextNode(' · '),pattern);}
 const [question,answers,correct,reason]=selected.question;
 $('complexity-question').textContent=question;$('complexity-answer-feedback').textContent='';$('complexity-answers').replaceChildren();
 complexityChoices(selected).forEach(({text,correct:isCorrect})=>{const button=document.createElement('button');button.textContent=text;button.type='button';button.onclick=()=>{$('complexity-answer-feedback').textContent=(isCorrect?'Correct. ':'Try the derivation again. ')+reason;};$('complexity-answers').append(button);});
 drawComplexityStep();drawComplexityWork();
}
function drawComplexityStep(){
 [...$('complexity-derivation').children].forEach((item,index)=>{item.hidden=index>complexityStep;item.classList.toggle('current-derivation',index===complexityStep);if(index===complexityStep)item.setAttribute('aria-current','step');else item.removeAttribute('aria-current');});
 $('complexity-step-status').textContent=`Derivation ${complexityStep+1} of ${selected.steps.length}: ${selected.steps[complexityStep][0]}`;
 $('complexity-back').disabled=complexityStep===0;$('complexity-next').disabled=complexityStep===selected.steps.length-1;
}
function drawComplexityWork(){
 try{const n=Number($('complexity-n').value);$('complexity-k').max=n;const k=selected.kind==='heap'?Number($('complexity-k').value):1;const result=complexityWork(selected.kind,n,k);$('complexity-n-value').textContent=n;$('complexity-counter').textContent=`${result.label}: ${result.count.toLocaleString()}`;$('complexity-formula').textContent=result.formula;$('complexity-memory').textContent=result.memory;$('complexity-work-error').textContent='';}
 catch(error){$('complexity-work-error').textContent=error.message;$('complexity-counter').textContent='';$('complexity-formula').textContent='';$('complexity-memory').textContent='';}
}
// Bind after the DOM exists, before the navigation module restores a lesson.
$('complexity-next').onclick=()=>{complexityStep=Math.min(selected.steps.length-1,complexityStep+1);drawComplexityStep();};
$('complexity-back').onclick=()=>{complexityStep=Math.max(0,complexityStep-1);drawComplexityStep();};
$('complexity-show-all').onclick=()=>{complexityStep=selected.steps.length-1;drawComplexityStep();};
$('complexity-n').addEventListener('input',()=>{const n=Number($('complexity-n').value),old=Number($('complexity-k').value);if(selected.kind==='heap'&&Number.isInteger(old)){const k=boundedHeapSize(n,old);$('complexity-k').value=k;$('complexity-adjustment').textContent=k!==old?`Adjusted k from ${old} to ${k} because k cannot exceed n.`:'';}drawComplexityWork();});$('complexity-k').addEventListener('input',drawComplexityWork);

function updateCourseProgress(){const index=complexityLessons.indexOf(selected),progress=readProgress();const count=complexityLessons.filter(c=>progress['complexity/'+c.id]).length;$('course-progress').textContent=`Chapter ${index+1} of ${complexityLessons.length} · ${count} completed on this device`;$('chapter-previous').disabled=index===0;$('chapter-next').disabled=index===complexityLessons.length-1;}
function openComplexityChapter(index){const chapter=complexityLessons[index];if(!chapter)return;selected=chapter;renderMenu();loadLesson();lessonChanged();$('title').tabIndex=-1;$('title').focus();}
$('chapter-previous').onclick=()=>openComplexityChapter(complexityLessons.indexOf(selected)-1);
$('chapter-next').onclick=()=>openComplexityChapter(complexityLessons.indexOf(selected)+1);
$('resume-complexity').onclick=()=>{setMode('complexity');$('title').tabIndex=-1;$('title').focus();};
$('worksheet-reveal').onclick=()=>{const output=$('worksheet-feedback');output.replaceChildren();const intro=document.createElement('p');intro.textContent='Compare your reasoning with the lesson. This is a self-assessment, not an automatic grade.';output.append(intro);for(const [heading,text]of selected.steps){const p=document.createElement('p'),strong=document.createElement('strong');strong.textContent=heading+': ';p.append(strong,document.createTextNode(text));output.append(p);}output.hidden=false;};
