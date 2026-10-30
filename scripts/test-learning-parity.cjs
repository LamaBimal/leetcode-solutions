// Compare the browser teaching implementations with actual Java APIs.
const fs=require('node:fs'),path=require('node:path'),vm=require('node:vm'),assert=require('node:assert/strict'),cp=require('node:child_process');
const root=path.resolve(__dirname,'..'),context=vm.createContext({});
vm.runInContext(fs.readFileSync(path.join(root,'docs/assets/lab-models.js'),'utf8')+';globalThis.models={lessons,trace};',context);
const {lessons,trace}=context.models;
const cases=lessons.map(l=>({id:l.id,input:JSON.parse(JSON.stringify(l.input)),param:l.param}));
for(const [id,input,param]of [['pair',[],0],['pair',[2,2],4],['prefix',[1,-1,1],0],['binary',[3,4,1,2],9],['heap',[2,2,1],2],['dp',0],['cycle',[7],0],['cycle',[],-1],['reverse',[]],['window',''],['stack',[]],['tree',[]],['bfs',[]],['interval',[]],['backtrack',[]],['matrix',[[2]],2],['dfs',[[0]]]])cases.push({id,input,param});
const array=a=>'new int[]{'+a.join(',')+'}',matrix=a=>'new int[][]{'+a.map(array).join(',')+'}';
function expression(c){const a=Array.isArray(c.input)?array(c.input):null,p=c.param??0;switch(c.id){
 case 'prefix':return `new com.learning.prefixsum.LongestSubArray().findLongestSubArrayWithTarget(${a},${p})`;
 case 'pair':return `new com.learning.twopointers.SumTwoNumber().findTarget(${a},${p})`;
 case 'window':return `new com.learning.slidingwindow.LargestSubString().lengthOfLongestSubstring(${JSON.stringify(c.input)})`;
 case 'stack':return `new com.learning.monotonicstack.NextGreaterElement().nextGreaterElement(${a})`;
 case 'heap':return `new com.learning.topkelements.KthLargestElement().findKthLargest(${a},${p})`;
 case 'binary':return `new com.learning.binarysearch.SearchRotatedArray().search(${a},${p})`;
 case 'dp':return `new com.learning.dynamicprogramming.ClimbingStairs().countWaysWithTabulation(${c.input})`;
 case 'interval':return `new com.learning.overlappingintervals.MergeIntervals().merge(${matrix(c.input)})`;
 case 'matrix':return `new com.learning.matrixtraversal.FloodFill().floodFill(${matrix(c.input)},0,0,${p})`;
 case 'dfs':return `new com.learning.dfs.NumberOfIslands().numIslands(new char[][]{${c.input.map(row=>"new char[]{"+row.map(v=>"'"+v+"'").join(',')+"}").join(',')}})`;
 case 'backtrack':return `new com.learning.backtracking.Subsets().subsets(${a})`;
 case 'cycle':return `cycle(${a},${p})`;
 case 'reverse':return `reverse(${a})`;
 case 'tree':return `new com.learning.binarytree.BinaryTreeTraversal().inorder(tree(new Integer[]{${c.input.join(',').replace(/,,/g,',null,')}},0))`;
 case 'bfs':return `new com.learning.bfs.BinaryTreeLevelOrder().levelOrder(level(new Integer[]{${c.input.map(v=>v===null?'null':v).join(',')}},0))`;
 default:throw Error(c.id);
}}
const source=`import java.util.*; import java.lang.reflect.Array;
public class GuideParity {
 static String json(Object x){if(x==null)return "null";if(x.getClass().isArray()){List<Object> a=new ArrayList<>();for(int i=0;i<Array.getLength(x);i++)a.add(Array.get(x,i));return json(a);}if(x instanceof Iterable){List<String>a=new ArrayList<>();for(Object v:(Iterable<?>)x)a.add(json(v));return "["+String.join(",",a)+"]";}return x.toString();}
 static boolean cycle(int[] a,int index){com.learning.fastslowpointers.LinkedListCycle.ListNode[] nodes=new com.learning.fastslowpointers.LinkedListCycle.ListNode[a.length];for(int i=0;i<a.length;i++)nodes[i]=new com.learning.fastslowpointers.LinkedListCycle.ListNode(a[i]);for(int i=0;i<a.length;i++)nodes[i].next=i+1<a.length?nodes[i+1]:index<0?null:nodes[index];return new com.learning.fastslowpointers.LinkedListCycle().hasCycle(a.length==0?null:nodes[0]);}
 static List<Integer> reverse(int[] a){com.learning.linkedlistreversal.ReverseLinkedList.ListNode head=null;for(int i=a.length-1;i>=0;i--){com.learning.linkedlistreversal.ReverseLinkedList.ListNode n=new com.learning.linkedlistreversal.ReverseLinkedList.ListNode(a[i]);n.next=head;head=n;}head=new com.learning.linkedlistreversal.ReverseLinkedList().reverse(head);List<Integer> out=new ArrayList<>();while(head!=null){out.add(head.val);head=head.next;}return out;}
 static com.learning.binarytree.BinaryTreeTraversal.TreeNode tree(Integer[] a,int i){if(i>=a.length||a[i]==null)return null;com.learning.binarytree.BinaryTreeTraversal.TreeNode n=new com.learning.binarytree.BinaryTreeTraversal.TreeNode(a[i]);n.left=tree(a,2*i+1);n.right=tree(a,2*i+2);return n;}
 static com.learning.bfs.BinaryTreeLevelOrder.TreeNode level(Integer[] a,int i){if(i>=a.length||a[i]==null)return null;com.learning.bfs.BinaryTreeLevelOrder.TreeNode n=new com.learning.bfs.BinaryTreeLevelOrder.TreeNode(a[i]);n.left=level(a,2*i+1);n.right=level(a,2*i+2);return n;}
 public static void main(String[] args){${cases.map(c=>'System.out.println(json('+expression(c)+'));').join('\n')}}}
`;
const out=path.join(root,'.cache/guide-parity');fs.mkdirSync(out,{recursive:true});fs.writeFileSync(path.join(out,'GuideParity.java'),source);
const sources=[...new Set(lessons.map(l=>path.join(root,'src/main/java/com/learning',l.path+'.java')))];
cp.execFileSync('javac',['-encoding','UTF-8','-d',out,...sources,path.join(out,'GuideParity.java')],{stdio:'pipe'});
const results=cp.execFileSync('java',['-cp',out,'GuideParity'],{encoding:'utf8'}).trim().split(/\r?\n/).map(JSON.parse);
cases.forEach((c,i)=>assert.deepEqual(JSON.parse(JSON.stringify(trace(c.id,c.input,c.param).at(-1).state.result)),results[i],c.id));
console.log(`Java/browser parity: ${cases.length} fixtures across all 15 algorithm lessons passed.`);
