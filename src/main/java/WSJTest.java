import net.didion.jwnl.Examples;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.dictionary.Dictionary;

public class WSJTest {
  public static void main(String[] args) throws Exception {
	  try {
          // initialize JWNL (this must be done before JWNL can be used)
          JWNL.initialize();
          new Examples().go();
          
      } catch (Exception ex) {
          ex.printStackTrace();
          System.exit(-1);
      }
  
    String[] words = new String[]{"bend","cat","clown","meet","bend",
        "turn","proceed","prevent","proposal","frolic","friendly","beautiful","very","loudly","ill-treatment"};
    for (int i = 0; i < words.length; ++i) {
      System.out.print(words[i]+":");
      int[] p = polysemy(words[i]);
      for (int j = 0; j < p.length; ++j)
        System.out.print(p[j]+((j<p.length-1)?",":"\r\n"));
    }
  }
  
  /**
   * Returns list of polysemy counts for word.
   * result[0] is count for noun, 1 for verb, 2 for adjective, 3 for adverb
   * @param word word to examine
   * @return
   */
  public static int[] polysemy(String word) throws JWNLException {
    int[] polysemies = new int[4];
    Dictionary d = Dictionary.getInstance();
    IndexWord noun_form = d.getIndexWord(POS.NOUN, word);
    polysemies[0] = (noun_form==null)?0:noun_form.getSenses().length;
    IndexWord verb_form = d.getIndexWord(POS.VERB, word);
    polysemies[1] = (verb_form==null)?0:verb_form.getSenses().length;
    IndexWord adj_form = d.getIndexWord(POS.ADJECTIVE, word);
    polysemies[2] = (adj_form==null)?0:adj_form.getSenses().length;
    IndexWord adv_form = d.getIndexWord(POS.ADVERB, word);
    polysemies[3] = (adv_form==null)?0:adv_form.getSenses().length;
    return polysemies;
  }
}