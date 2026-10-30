// Run from any directory: node scripts/test-learning-lab.cjs
// Uses only Node built-ins. Browser presentation is checked separately.
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const assert = require('node:assert/strict');
const html = fs.readFileSync(path.join(__dirname, '../docs/learning-lab.html'), 'utf8');
const script = ['lab-models.js','lab-ui.js'].map(name => fs.readFileSync(path.join(__dirname, '../docs/assets',name),'utf8')).join('\n');
new vm.Script(script); // Parse the entire application, including UI wiring.
const models = script.split('// End of pure teaching models.')[0];
const context = vm.createContext({});
vm.runInContext(models + '\nglobalThis.lab = {lessons, concepts, trace, runScenario};', context);
const {lessons, concepts, trace, runScenario} = context.lab;
const plain = x => JSON.parse(JSON.stringify(x));
const result = (id, input, param) => plain(trace(id, input, param).at(-1).state.result);
let checks = 0;
function check(actual, expected) { assert.deepEqual(actual, expected); checks++; }
const expected = {prefix:4,pair:true,window:3,cycle:true,reverse:[5,4,3,2,1],stack:[5,5,6,6,-1],heap:5,interval:[[1,6],[8,10],[15,18]],binary:4,tree:[1,2,3,4,5,6,7],dfs:3,bfs:[[3],[9,20],[15,7]],matrix:[[2,2,2],[2,2,0],[2,0,1]],backtrack:[[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]],dp:13};
for (const lesson of lessons) {
 assert(fs.existsSync(path.join(__dirname, '../src/main/java/com/learning', lesson.path + '.java')));
 const input = plain(lesson.input), before = plain(input);
 const frames = trace(lesson.id, input, lesson.param);
 check(plain(frames.at(-1).state.result), expected[lesson.id]);
 check(input, before); // Teaching traces must never modify the supplied object.
 assert(frames.every(f => typeof f.message === 'string' && f.message.length));
}
for (const id of ['prefix','pair','window','cycle','reverse','stack','interval','binary','tree','bfs','backtrack']) {
 const input = id === 'window' ? '' : [];
 const param = id === 'cycle' ? -1 : 0;
 const empty = {prefix:0,pair:false,window:0,cycle:false,reverse:[],stack:[],interval:[],binary:-1,tree:[],bfs:[],backtrack:[[]]};
 check(result(id,input,param),empty[id]);
}
check(result('dp',0),1);
check(result('matrix',[[2,2],[2,1]],2),[[2,2],[2,1]]);
check(result('cycle',[9],0),true);
check(result('cycle',[9],-1),false);
for (const [id,input,param] of [['pair',[2,1],3],['binary',[2,1,3],1],['binary',[1,1],1],['heap',[],1],['heap',[1],2],['cycle',[1],2],['interval',[[2,1]]],['tree',[null,1]],['dfs',[[1],[1,0]]],['matrix',[],2],['backtrack',[1,1]],['dp',25],['window','ðŸ™‚'],['prefix',[1.5],2]]) {
 assert.throws(() => trace(id,input,param)); checks++;
}
// Independent small exhaustive oracles; repeatable pseudo-random inputs.
let seed=2197;
const random=n=>{seed=(Math.imul(seed,1664525)+1013904223)>>>0;return seed%n;};
for(let sample=0;sample<150;sample++){
 const a=Array.from({length:random(10)},()=>random(15)-7),target=random(19)-9;
 let longest=0,pair=false;
 for(let i=0;i<a.length;i++){
  let sum=0;
  for(let j=i;j<a.length;j++){sum+=a[j];if(sum===target)longest=Math.max(longest,j-i+1);}
  for(let j=i+1;j<a.length;j++)if(a[i]+a[j]===target)pair=true;
 }
 check(result('prefix',a,target),longest);
 const sorted=[...a].sort((x,y)=>x-y);
 check(result('pair',sorted,target),pair);
 const next=a.map((v,i)=>a.slice(i+1).find(x=>x>v)??-1);
 check(result('stack',a),next);
 if(a.length){const k=1+random(a.length);check(result('heap',a,k),sorted[a.length-k]);}
 const unique=[...new Set(sorted)],cut=random(unique.length+1),rotated=unique.slice(cut).concat(unique.slice(0,cut));
 check(result('binary',rotated,target),rotated.indexOf(target));
 const text=Array.from({length:random(12)},()=>String.fromCharCode(97+random(4))).join('');
 let best=0;
 for(let i=0;i<text.length;i++)for(let j=i+1;j<=text.length;j++)if(new Set(text.slice(i,j)).size===j-i)best=Math.max(best,j-i);
 check(result('window',text),best);
}
for(const lesson of concepts){
 if(lesson.path)assert(fs.existsSync(path.join(__dirname, '../src/main/java/com/learning/solid', lesson.path + '.java')));
 for(const after of [false,true])assert(runScenario(lesson.id,after,10).steps.length);
}
check(plain(runScenario('lsp',false,10).result),{expectedRectangleArea:50,actualArea:25,contractHolds:false});
check(runScenario('lsp',true,10).result.contractHolds,true);
check(runScenario('adapter',true,10).result.chargedCents,1000);
check(runScenario('adapter',false,10).result.chargedCents,10);
check(runScenario('command',true,10).result,0);
check(runScenario('command',false,10).result,10);
check(runScenario('strategy',true,10).result,8);
assert.throws(()=>runScenario('strategy',true,-1));
console.log(`Learning lab: ${checks} assertions passed; 15 algorithm defaults, edge cases, independent oracles, and 11 design lessons verified.`);

// Check the dedicated complexity curriculum against independent count formulas.
const complexityContext=vm.createContext({});
vm.runInContext(fs.readFileSync(path.join(__dirname,'../docs/assets/complexity-lessons.js'),'utf8')+';globalThis.curriculum={complexityLessons,complexityWork};',complexityContext);
const {complexityLessons,complexityWork}=complexityContext.curriculum;
assert.equal(complexityLessons.length,14);
assert.equal(new Set(complexityLessons.map(l=>l.id)).size,14);
for(const lesson of complexityLessons){
 assert(lesson.steps.length>=5 && lesson.question[1][lesson.question[2]]);
 if(lesson.source)assert(fs.existsSync(path.join(__dirname,'../src/main/java/com/learning',lesson.source+'.java')));
 if(lesson.pattern)assert(lessons.some(l=>l.id===lesson.pattern));
 for(const n of [1,2,8,16,20])assert(Number.isFinite(complexityWork(lesson.kind,n,1).count));
}
for(let n=1;n<=20;n++){
 let pairVisits=0;for(let i=0;i<n;i++)for(let j=i+1;j<n;j++)pairVisits++;
 assert.equal(complexityWork('triangular',n,1).count,pairVisits);
 assert.equal(complexityWork('pairs',n,1).count,n*n);
 assert.equal(complexityWork('pointers',n,1).count,n-1);
 assert.equal(complexityWork('halving',n,1).count,Math.floor(Math.log2(n))+1);
 assert.equal(complexityWork('dp',n,1).count,n-1);
}
assert.equal(complexityWork('heap',8,3).count,16);
assert.equal(complexityWork('subsets',4,1).count,16);
assert.throws(()=>complexityWork('scan',0,1));
assert.throws(()=>complexityWork('heap',4,5));
for(const name of ['complexity-lessons.js','complexity-ui.js','lab-enhancements.js'])new vm.Script(fs.readFileSync(path.join(__dirname,'../docs/assets',name),'utf8'));
console.log('Dedicated Big O module: 14 chapters, repository links, count derivations, and input boundaries verified.');

// Terminal highlights must agree with the result, including empty inputs.
for(const [id,input,param,line,result] of [
 ['pair',[1,2,4,7,11],9,4,true],['pair',[1,2],9,7,false],['pair',[],0,7,false],
 ['binary',[4,5,6,7,0,1,2],0,3,4],['binary',[1,2,3],9,6,-1],['binary',[],0,6,-1]
]){const final=trace(id,input,param).at(-1);assert.equal(final.outlineLine,line);assert.equal(final.state.result,result);}
vm.runInContext('globalThis.review={complexityChoices,boundedHeapSize};',complexityContext);
const choices=complexityLessons.map(lesson=>complexityContext.review.complexityChoices(lesson));
assert(new Set(choices.map(list=>list.findIndex(c=>c.correct))).size===3);
choices.forEach((list,i)=>{assert.equal(list.filter(c=>c.correct).length,1);assert.equal(list.find(c=>c.correct).text,complexityLessons[i].question[1][complexityLessons[i].question[2]]);});
assert.equal(complexityContext.review.boundedHeapSize(1,3),1);
assert.equal(complexityContext.review.boundedHeapSize(8,3),3);
console.log('Review regressions: terminal highlights, answer identity/order, and heap constraints passed.');
