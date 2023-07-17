package units;

import java.util.ArrayList;

public interface CharacterInterface {
  void step(ArrayList<Character> teamFoe, ArrayList<Character> teamFriend);
  String getInfo();

}
