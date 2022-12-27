package oy.tol.tra;

public class HashBrailleTable extends BrailleTable {

   // TODO: Implement this without using 67-phonebook hash table nor Java hash tables.
   // Make sure you understand the required limited ways to implement this task!
   // Ask teachers to be sure!
   private BrailleSymbol[] charTable;
   private int[] brailleRelation;
   private static final int TABLE_SIZE = 70;
   @Override
   protected void initializeTable() {
      charTable = new BrailleSymbol[TABLE_SIZE];
      brailleRelation = new int[TABLE_SIZE];
   }

   @Override
   protected void addToTable(Character character, Character brailleSymbol) {
      int charIndex = character % TABLE_SIZE;
      charTable[charIndex] = new BrailleSymbol(character, brailleSymbol);
      brailleRelation[brailleSymbol % TABLE_SIZE] = charIndex;
   }

   @Override
   public Character lookupBrailleSymbol(Character forCharacter) {
      int index;
      if(forCharacter >= 32 && forCharacter <= 96){
         index = forCharacter % TABLE_SIZE;
      }
      else if(forCharacter >= 97 && forCharacter <= 122){
         index = (forCharacter & 65503) % TABLE_SIZE;
      }
      else if(forCharacter == 228 || forCharacter == 229 || forCharacter == 246){
         index = (forCharacter - 32) % TABLE_SIZE;
      }
      else{
         index = Character.toUpperCase(forCharacter) % TABLE_SIZE;
      }
      
      if(charTable[index] == null){
         return UNKNOWN_CHAR_OR_SYMBOL;
      }
      return charTable[index].braille;
   }

   @Override
   public Character lookupCharacter(Character forBrailleSymbol) {
      int index = forBrailleSymbol % TABLE_SIZE;
      return charTable[brailleRelation[index]] == null ? UNKNOWN_CHAR_OR_SYMBOL: charTable[brailleRelation[index]].character;
   }

}
