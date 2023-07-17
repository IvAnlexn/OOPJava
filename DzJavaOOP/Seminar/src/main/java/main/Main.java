package main;

import units.Character;
import units.*;
import views.View;

import java.util.*;

public class Main {
  public static ArrayList<Character> team1 = new ArrayList<>(); // Создание списка для команды 1
  public static ArrayList<Character> team2 = new ArrayList<>(); // Создание списка для команды 2
  public static ArrayList<Character> allTeam = new ArrayList<>(); // Создание списка, содержащего всех персонажей

  public static void main(String[] args) {
    fillGreenList(team1, 1); // Заполнение списка команды 1 зелеными персонажами
    fillBlueList(team2, 10); // Заполнение списка команды 2 синими персонажами
    allTeam.addAll(team1); // Добавление всех персонажей из команды 1 в общий список
    allTeam.addAll(team2); // Добавление всех персонажей из команды 2 в общий список
    allTeam.sort(new Comparator<Character>() { // Сортировка общего списка по инициативе персонажей
      @Override
      public int compare(Character character, Character t1) {
        if (character.getInitiative() > t1.getInitiative()) return -1; // Сравнение инициативы персонажей
        if (character.getInitiative() < t1.getInitiative()) return 1;
        return 0;
      }
    });
    Scanner scanner = new Scanner(System.in); // Создание объекта Scanner для чтения ввода пользователя
    while (isAtLeastOneAlive(team1) && isAtLeastOneAlive(team2)) { // Пока есть живые персонажи в обеих командах
      View.view(); // Отображение игрового интерфейса
      for (Character c : allTeam) { // Проход по всем персонажам в общем списке
        if (team1.contains(c)) { // Если персонаж находится в команде 1
          c.step(team2, team1); // Перемещение персонажа команды 1
        } else { // Если персонаж находится в команде 2
          c.step(team1, team2); // Перемещение персонажа команды 2
        }
      }
      scanner.nextLine(); // Ожидание ввода пользователя
    }
    View.view(); // Отображение окончательного состояния игрового интерфейса
  }

  public static boolean isAtLeastOneAlive(ArrayList<Character> team) {
    for (Character c : team) { // Проверка каждого персонажа в команде
      if (!c.isDead()) return true; // Если хотя бы один персонаж жив, возвращаем true
    }
    return false; // Если все персонажи мертвы, возвращаем false
  }

  public static void fillGreenList(ArrayList<Character> list, int startRow) {
    Names[] names = Names.values(); // Получение всех значений из перечисления Names
    for (int i = 1; i <= 10; i++) { // Заполнение списка десятью персонажами
      int cnt = new Random().nextInt(4); // Случайное число от 0 до 3
      Names name = names[new Random().nextInt(names.length)]; // Случайное имя из перечисления Names
      switch (cnt) { // Выбор персонажа в зависимости от случайного числа
        case 0: {
          list.add(new Farmer(name, startRow, i)); // Добавление фермера в список
          break;
        }
        case 1: {
          list.add(new Mage(name, startRow, i)); // Добавление мага в список
          break;
        }
        case 2: {
          list.add(new Archer(name, startRow, i)); // Добавление лучника в список
          break;
        }
        default: {
          list.add(new Spearman(name, startRow, i)); // Добавление копейщика в список
          break;
        }
      }
    }
  }

  public static void fillBlueList(ArrayList<Character> list, int startRow) {
    Names[] names = Names.values(); // Получение всех значений из перечисления Names
    for (int i = 1; i <= 10; i++) { // Заполнение списка десятью персонажами
      int cnt = new Random().nextInt(4); // Случайное число от 0 до 3
      Names name = names[new Random().nextInt(names.length)]; // Случайное имя из перечисления Names
      switch (cnt) { // Выбор персонажа в зависимости от случайного числа
        case 0: {
          list.add(new Farmer(name, startRow, i)); // Добавление фермера в список
          break;
        }
        case 1: {
          list.add(new Priest(name, startRow, i)); // Добавление священника в список
          break;
        }
        case 2: {
          list.add(new Crossbowman(name, startRow, i)); // Добавление арбалетчика в список
          break;
        }
        default: {
          list.add(new Rogue(name, startRow, i)); // Добавление разбойника в список
          break;
        }
      }
    }
  }
}