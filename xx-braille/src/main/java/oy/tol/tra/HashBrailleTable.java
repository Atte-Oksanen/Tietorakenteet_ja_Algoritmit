package oy.tol.tra;

public class HashBrailleTable extends BrailleTable {
   private BrailleSymbol[] charTable;
   private int[] brailleRelation;
   private static final int TABLE_SIZE = 90;
   @Override
   protected void initializeTable() {
      charTable = new BrailleSymbol[TABLE_SIZE];
      brailleRelation = new int[TABLE_SIZE];
   }

   @Override
   protected void addToTable(Character character, Character brailleSymbol) {
      int charIndex = Character.toLowerCase(character) % TABLE_SIZE;
      charTable[charIndex] = new BrailleSymbol(character, brailleSymbol);
      brailleRelation[brailleSymbol % TABLE_SIZE] = charIndex;
   }

   @Override
   public Character lookupBrailleSymbol(Character forCharacter) {
      int index = Character.toLowerCase(forCharacter) % TABLE_SIZE;
      return charTable[index] == null ? UNKNOWN_CHAR_OR_SYMBOL: charTable[index].braille;
   }

   @Override
   public Character lookupCharacter(Character forBrailleSymbol) {
      int index = forBrailleSymbol % TABLE_SIZE;
      return charTable[brailleRelation[index]] == null ? UNKNOWN_CHAR_OR_SYMBOL: charTable[brailleRelation[index]].character;
   }

}
