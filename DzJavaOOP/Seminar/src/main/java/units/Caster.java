package units;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Caster extends Character implements CharacterInterface {
  int mana;
  int maxMana;

  public Caster(Names name, int hp, int maxHp, int damage, int defense, int initiative, int mana, int maxMana, int row, int col) {
    super(name, hp, maxHp, damage, defense, initiative, row, col);
    this.mana = mana;
    this.maxMana = maxMana;
  }

  private Character findMostDamaged(ArrayList<Character> team) {
    if (team.size() == 0) return null;
    Character mostDamaged = team.get(0);
    for (Character character : team) {
      if (!character.state.equals(States.DEAD)
              && character.hp < character.maxHp
              && character.hp/character.maxHp < mostDamaged.hp/mostDamaged.maxHp) {
        mostDamaged = character;
      }
    }
    if (mostDamaged.hp == mostDamaged.maxHp) {
      return null;
    }
    return mostDamaged;
  }

  public void step(ArrayList<Character> teamFoe, ArrayList<Character> teamFriend) {
    if (this.isDead()) return;
    if (mana < maxMana) mana += 1;
    if (mana < damage) {
      state = States.NOMANA;
      return;
    }
    Character damagedFriend = findMostDamaged(getNotDeadTeamMembers(teamFriend));
    if (damagedFriend != null) {
      damagedFriend.getHealing(damage);
      mana -= damage;
      state = States.CAST;
    }
  }

  @Override
  public String getInfo() {
    return super.getInfo() + String.format(" mana: %d/%d", this.mana, this.maxMana);
  }
}
