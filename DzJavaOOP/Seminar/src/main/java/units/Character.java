package units;

import map.Coordinates;

import java.util.ArrayList;
  /**
   В классе Character определены основные атрибуты и методы для персонажей. 
   Он является абстрактным классом, от которого наследуются конкретные классы персонажей, 
   такие как Farmer, Mage, Archer, Spearman и т.д. Комментарии объясняют функциональность 
   каждой части кода, включая инициализацию атрибутов, методы для получения и изменения данных, 
   а также методы для работы с состоянием персонажа и взаимодействия с другими персонажами. 
   */
  public abstract class Character implements CharacterInterface {
  protected Names name; // Имя персонажа
  protected int hp; // Текущее количество очков здоровья
  protected int maxHp; // Максимальное количество очков здоровья
  protected int damage; // Урон, наносимый персонажем
  protected int defense; // Защита персонажа
  protected int initiative; // Инициатива персонажа
  protected Coordinates position; // Позиция персонажа на карте
  protected States state; // Состояние персонажа (готов, мертв и т. д.)

  public Character(Names name, int hp, int maxHp, int damage, int defense, int initiative, int row, int col) {
    this.name = name; // Инициализация имени
    this.hp = hp; // Инициализация количества очков здоровья
    this.maxHp = maxHp; // Инициализация максимального количества очков здоровья
    this.damage = damage; // Инициализация урона
    this.defense = defense; // Инициализация защиты
    this.initiative = initiative; // Инициализация инициативы
    this.position = new Coordinates(row, col); // Создание объекта координат персонажа
    this.state = States.READY; // Инициализация состояния персонажа как "готов"
  }

  public Coordinates getCoordinates() {
    return position; // Возвращает координаты персонажа
  }

  protected Character findNearest(ArrayList<Character> team) {
    if (team.size() == 0) return null;
    Character nearest = team.get(0);
    for (Character character : team) {
      if (!character.state.equals(States.DEAD)
              && this != character
              && position.getDistance(character.getCoordinates()) < position.getDistance(nearest.getCoordinates())) {
        nearest = character;
      }
    }
    return nearest; // Возвращает ближайшего живого персонажа из команды
  }

  ArrayList<Character> getNotDeadTeamMembers(ArrayList<Character> team) {
    ArrayList<Character> notDeadTeamMembers = new ArrayList<>();
    for (Character c: team) {
      if (!c.isDead()) notDeadTeamMembers.add(c);
    }
    return notDeadTeamMembers; // Возвращает список живых персонажей из команды
  }

  protected void getDamage(int damagePoints) {
    hp -= damagePoints; // Вычитает урон из текущего количества очков здоровья
    if (hp <= 0) {
      hp = 0;
      state = States.DEAD; // Устанавливает состояние персонажа как "мертвый", если очки здоровья меньше или равны 0
    }
  }

  public boolean isDead() {
    return state.equals(States.DEAD); // Возвращает true, если персонаж мертв, иначе false
  }

  protected void getHealing(int healPoints) {
    hp += healPoints; // Прибавляет количество очков здоровья излечения к текущим очкам здоровья
    if (hp > maxHp) hp = maxHp; // Устанавливает текущие очки здоровья равными максимальным, если превышают их значение
  }

  public String getInfo() {
    return String.format("nm: %s, cl: %s, st: %s, hp: %d/%d, dmg: %d, def: %d, init: %d,", this.name.name(), this.toString(), this.state.name(), this.hp, this.maxHp, this.damage, this.defense, this.initiative);
  }

  public int getInitiative() {
    return this.initiative; // Возвращает инициативу персонажа
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName(); // Возвращает имя класса персонажа
  }
}