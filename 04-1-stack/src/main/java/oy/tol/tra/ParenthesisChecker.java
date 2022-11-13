package oy.tol.tra;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * @param stack The stack object used in checking the parentheses from the string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both opening and closing).
    * @throws ParenthesesException if the parentheses did not match as intended.
    * @throws StackAllocationException If the stack cannot be allocated or reallocated if necessary.
    */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      int parentheses = 0;
      char popped;

      for(int n = 0; n < fromString.length(); n++){
         char testable = fromString.charAt(n);
         
         if(testable == '('|| testable == '[' || testable == '{'){ //Checking for opening brackets
            try {
               stack.push(testable);
            } catch (StackAllocationException e) {
               throw new StackAllocationException("The stack could not be allocated.");  //Failure while pushing opening bracket to stack
            }
            parentheses++;
         }
         else if(testable == ')'|| testable == ']' || testable == '}'){ //Checking for closing brackets
            parentheses++;
            try {
               popped = stack.pop();
            } catch (Exception e) {
               throw new ParenthesesException("There are too many closing parentheses.", -1); //Failure while popping opening bracket from stack
            }
            
            switch (testable) { //Testing for the same kind of opening and closing brackets
               case ')':
                  if(popped == '('){
                     break;
                  }
               case ']':
                  if(popped == '['){
                     break;
                  }
               
               case '}':
                  if(popped == '{'){
                     break;
                  }
         
               default:
                  throw new ParenthesesException("The parentheses did not match.", -3);
            }
         }
      }

      if(stack.isEmpty() == false){
         throw new ParenthesesException("There are too many opening parentheses", -2); //Failure when the stack is not empty
      }

      return parentheses;
   }
}
