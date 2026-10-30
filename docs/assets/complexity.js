// Pure counted examples. The table never executes exponential enumeration.
function complexityCounts(n) {
 if (!Number.isInteger(n) || n < 1 || n > 40) throw new Error('n must be 1–40');
 let linear=0, pairs=0, halving=0;
 for(let i=0;i<n;i++) linear++;
 for(let i=0;i<n;i++) for(let j=0;j<n;j++) pairs++;
 for(let remaining=n;remaining>0;remaining=Math.floor(remaining/2)) halving++;
 return {constant:1,halving,linear,pairs,mergeWork:n*Math.ceil(Math.log2(n)),subsets:2**n};
}
if (typeof document !== 'undefined') {
 const rows=[['Read one array cell','Θ(1)','constant','Θ(1)'],['Repeatedly halve an integer','Θ(log(n + 1))','halving','Θ(1)'],['Visit every element','Θ(n)','linear','Θ(1)'],['n items across ceil(log₂ n) merge levels (work model)','Θ(n log n)','mergeWork','Θ(n) for a merge buffer'],['Visit every ordered pair','Θ(n²)','pairs','Θ(1)'],['Count all subsets (formula only)','Θ(2ⁿ) subsets','subsets','Θ(n) recursion; output additional']];
 function drawGrowth(){const n=Number(document.getElementById('growth-n').value),a=complexityCounts(n),b=complexityCounts(2*n);document.getElementById('growth-n-value').textContent=n;const body=document.getElementById('growth-rows');body.replaceChildren();for(const [name,bound,key,space]of rows){const row=document.createElement('tr');for(const text of [name,bound,a[key].toLocaleString(),b[key].toLocaleString(),space]){const cell=document.createElement('td');cell.textContent=text;row.append(cell);}body.append(row);}document.getElementById('growth-status').textContent=`Doubling ${n} to ${2*n}: linear work doubles, ordered-pair work quadruples. Logarithmic growth depends on integer boundaries.`;}
 document.getElementById('growth-n').addEventListener('input',drawGrowth);drawGrowth();
 document.getElementById('growth-check').addEventListener('click',()=>{const answer=document.querySelector('[name="big-o-answer"]:checked');document.getElementById('growth-feedback').textContent=!answer?'Choose an answer first.':answer.value==='linear'?'Correct: n + n = 2n, so Θ(n). Nesting independent n-length loops would give Θ(n²).':'The loops are consecutive: add their work. n + n = 2n, so Θ(n).';});
 // Storage is optional: private browsing and file URLs may deny access.
 for(const box of document.querySelectorAll('[data-complete]')){const key='guide-progress-v1:'+location.pathname+':'+box.dataset.complete;try{box.checked=localStorage.getItem(key)==='true';}catch{}box.addEventListener('change',()=>{try{localStorage.setItem(key,String(box.checked));}catch{}});}
}
