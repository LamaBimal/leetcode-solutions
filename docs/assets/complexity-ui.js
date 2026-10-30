// Dedicated Big O module. Shared counters do not execute repository Java code.
let complexityStep=0;
function renderComplexityLesson(){
 complexityStep=0;
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
 answers.forEach((text,index)=>{const button=document.createElement('button');button.textContent=text;button.type='button';button.onclick=()=>{$('complexity-answer-feedback').textContent=(index===correct?'Correct. ':'Try the derivation again. ')+reason;};$('complexity-answers').append(button);});
 drawComplexityStep();drawComplexityWork();
}
function drawComplexityStep(){
 [...$('complexity-derivation').children].forEach((item,index)=>{item.hidden=index>complexityStep;item.classList.toggle('current-derivation',index===complexityStep);if(index===complexityStep)item.setAttribute('aria-current','step');else item.removeAttribute('aria-current');});
 $('complexity-step-status').textContent=`Derivation ${complexityStep+1} of ${selected.steps.length}: ${selected.steps[complexityStep][0]}`;
 $('complexity-back').disabled=complexityStep===0;$('complexity-next').disabled=complexityStep===selected.steps.length-1;
}
function drawComplexityWork(){
 try{const n=Number($('complexity-n').value),k=selected.kind==='heap'?Number($('complexity-k').value):1;const result=complexityWork(selected.kind,n,k);$('complexity-n-value').textContent=n;$('complexity-counter').textContent=`${result.label}: ${result.count.toLocaleString()}`;$('complexity-formula').textContent=result.formula;$('complexity-memory').textContent=result.memory;$('complexity-work-error').textContent='';}
 catch(error){$('complexity-work-error').textContent=error.message;$('complexity-counter').textContent='';$('complexity-formula').textContent='';$('complexity-memory').textContent='';}
}
// Bind after the DOM exists, before the navigation module restores a lesson.
$('complexity-next').onclick=()=>{complexityStep=Math.min(selected.steps.length-1,complexityStep+1);drawComplexityStep();};
$('complexity-back').onclick=()=>{complexityStep=Math.max(0,complexityStep-1);drawComplexityStep();};
$('complexity-show-all').onclick=()=>{complexityStep=selected.steps.length-1;drawComplexityStep();};
$('complexity-n').addEventListener('input',drawComplexityWork);$('complexity-k').addEventListener('input',drawComplexityWork);
