/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class FlattenNestedListIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> myStack; 
    NestedInteger nextEle;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.myStack = new Stack<>();
        myStack.push(nestedList.iterator());
        advance();
    }

    private void advance(){
        nextEle = null;
        while(!myStack.isEmpty()){
            if(!myStack.peek().hasNext()){
                myStack.pop();
                nextEle = null;
            }
            else{
                nextEle = myStack.peek().next();
                if(nextEle.isInteger()){
                    break;
                }
                else{
                    myStack.push(nextEle.getList().iterator());
                }
            }
        }
    }

    @Override
    public Integer next() {
        Integer temp = nextEle.getInteger();
        advance();
        return temp;
    }

    @Override
    public boolean hasNext() {
        return nextEle != null;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */