package units;

import map.Coordinates;
import map.Directions;

import java.util.ArrayList;

public abstract class Warrior extends Character implements CharacterInterface {
  public Warrior(Names name, int hp, int maxHp, int damage, int defense, int initiative, int row, int col) {
    super(name, hp, maxHp, damage, defense, initiative, row, col);
  }

  @Override
  public void step(ArrayList<Character> teamFoe, ArrayList<Character> teamFriend) {
    if (this.isDead()) return;
    Character nearestFoe = findNearest(getNotDeadTeamMembers(teamFoe));
    if (nearestFoe == null) return;
    if (this.attack(nearestFoe)) return;
    this.move(nearestFoe, getNotDeadTeamMembers(teamFriend), getNotDeadTeamMembers(teamFoe));
  }

  @Override
  public String getInfo() {
    return super.getInfo();
  }

  public boolean attack(Character enemy) {
    if (this.getCoordinates().getDistance(enemy.getCoordinates()) == 1) {
      enemy.getDamage(damage);
      state = States.ATTACK;
      return true;
    }
    return false;
  }

  public void move(Character enemy, ArrayList<Character> team1, ArrayList<Character> team2) {
    Directions direction = this.getCoordinates().getDirection(enemy.getCoordinates());
    switch (direction) {
      case SOUTH:
        if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0] + 1, this.getCoordinates().toArray()[1]))
                && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0] + 1, this.getCoordinates().toArray()[1])))
          this.position.setCoordinates(this.getCoordinates().toArray()[0] + 1, this.getCoordinates().toArray()[1]);
        state = States.MOVE;
        break;
      case NORTH:
        if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0] - 1, this.getCoordinates().toArray()[1]))
                && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0] - 1, this.getCoordinates().toArray()[1])))
          this.position.setCoordinates(this.getCoordinates().toArray()[0] - 1, this.getCoordinates().toArray()[1]);
        state = States.MOVE;
        break;
      case WEST:
        if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] - 1))
                && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] - 1)))
          this.position.setCoordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] - 1);
        state = States.MOVE;
        break;
      case EAST:
        if (this.checkStepAheadIsAvailable(team1, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] + 1))
                && this.checkStepAheadIsAvailable(team2, new Coordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] + 1)))
          this.position.setCoordinates(this.getCoordinates().toArray()[0], this.getCoordinates().toArray()[1] + 1);
        state = States.MOVE;
        break;
    }
  }


  private boolean checkStepAheadIsAvailable(ArrayList<Character> team, Coordinates coordinates) {
    for (Character character : team) {
      if (!character.isDead() && coordinates.isEqual(character.getCoordinates())) return false;
    }
    return true;
  }
}

