package units;

import java.util.ArrayList;

public abstract class Shooter extends Character implements CharacterInterface {
  protected int range; // Дальность стрельбы
  protected int arrows; // Текущее количество стрел
  protected int maxArrows; // Максимальное количество стрел

  public Shooter(Names name, int hp, int maxHp, int damage, int defense, int initiative, int arrows, int maxArrows, int row, int col) {
    super(name, hp, maxHp, damage, defense, initiative, row, col); // Вызов конструктора суперкласса Character
    this.arrows = arrows; // Инициализация количества стрел
    this.maxArrows = maxArrows; // Инициализация максимального количества стрел
  }

  @Override
  public void step(ArrayList<Character> teamFoe, ArrayList<Character> teamFriend) {
    if (this.isDead()) return; // Если персонаж мертв, выход из метода
    this.state = States.READY; // Установка состояния персонажа как "готов"
    if (arrows <= 0) {
      this.state = States.NOAMMO; // Если нет стрел, установка состояния персонажа как "нет боеприпасов"
      return;
    }
    Character nearestFoe = findNearest(getNotDeadTeamMembers(teamFoe)); // Поиск ближайшего вражеского персонажа
    if (nearestFoe != null) {
      nearestFoe.getDamage(damage); // Нанесение урона ближайшему вражескому персонажу
      this.arrows -= 1; // Уменьшение количества стрел на 1
      state = States.SHOOT; // Установка состояния персонажа как "стрельба"
    }
    for (Character c : getNotDeadTeamMembers(teamFriend)) {
      if (c == null) return;
      if (this.arrows < this.maxArrows && c.getClass() == Farmer.class && c.state.equals(States.READY)) {
        this.arrows += 1; // Увеличение количества стрел на 1
        c.state = States.SUPPLY; // Установка состояния фермера как "пополнение"
        break;
      }
    }
  }

  @Override
  public String getInfo() {
    return super.getInfo() + String.format(" ammo: %d/%d", this.arrows, this.maxArrows); // Возвращает информацию о персонаже и количестве стрел
  }
}







