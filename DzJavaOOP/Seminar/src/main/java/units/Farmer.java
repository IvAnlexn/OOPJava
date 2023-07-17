package units;

import java.util.ArrayList;

public class Farmer extends Character{

  public Farmer(Names name, int row, int col) {
    super(name, 4, 4, 1, 1, 1, row, col);
  }

  @Override
  public void step(ArrayList<Character> team, ArrayList<Character> team2){
    if (this.isDead()) return;
    state = States.READY;
  }
  }
