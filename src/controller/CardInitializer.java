package controller;

import model.*;
import model.Buff.*;
import model.Cards.Hero;
import model.Cards.Minion;
import model.Cards.SoldierTypes;
import model.Cards.SpellCard;
import model.Target.SoldierTargetType;
import model.Target.Target;
import model.Target.Type;

import java.util.ArrayList;

public class CardInitializer {
    private ArrayList<Minion> minionCards = new ArrayList<>();
    private ArrayList<SpellCard> spellCards = new ArrayList<>();
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private Shop shop = Shop.getInstance();

    private final static CardInitializer CARD_INITIALIZER = new CardInitializer();

    public static CardInitializer getInstance() {
        return CARD_INITIALIZER;
    }

    private CardInitializer() {
    }

    public void createCards() {
        ArrayList<Minion> minionCards = new ArrayList<>();
        ArrayList<SpellCard> spellCards = new ArrayList<>();
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        createMinions(minionCards, new ArrayList<Buff>());
        createHeroes(heroes);
        createSpellCards(spellCards, new ArrayList<Buff>());
        createItem(items, new ArrayList<Buff>());
        for (Minion minionCard : minionCards) {
            shop.getCards().add(minionCard);
        }
        for (SpellCard spellCard : spellCards) {
            shop.getCards().add(spellCard);
        }
        for (Hero hero : heroes) {
            shop.getCards().add(hero);
        }
        for (Item item : items) {
            shop.getItems().add(item);
        }
    }

    public void createMinions(ArrayList<Minion> minions, ArrayList<Buff> buffs) {
        Minion camandareFars = new Minion(101, "camandareFarse", 300, 2, 4, 6,
                SoldierTypes.RANGED, 7, null, null, null);
        minions.add(camandareFars);

        buffs.add(new StunBuff(Kind.POSITIVE, 1, false));
        Minion shamshirZaneFars = new Minion(102, "shamshir zane fars", 400, 2, 6, 4,
                SoldierTypes.MELEE, 0, "stun enemy for one turn", AbilityCastTime.ON_ATTACK, buffs);
        minions.add(shamshirZaneFars);

        Minion neyzeDareFars = new Minion(103, "neyzedare Farse", 500, 1, 3, 5,
                SoldierTypes.HYBRID, 3, null, null, null);
        minions.add(neyzeDareFars);

        Minion asbSavarFars = new Minion(104, "asbsavare Fars", 200, 4, 6, 10,
                SoldierTypes.MELEE, 0, null, null, null);
        minions.add(asbSavarFars);

//        buffs = new ArrayList<>();
//        Buff buff = new buff()
        Minion pahlavaneFarse = new Minion(105, "pahlavane Fars", 600, 9, 6, 24,
                SoldierTypes.MELEE, 0, "damage enemy", null, null);
        minions.add(pahlavaneFarse);
//
    }

    public void createHeroes(ArrayList<Hero> heroes) {
        Buff buff = new PowerBuff(Kind.POSITIVE, 0, true, 0, 4);
        Hero diveSepid = new Hero(301, "dive sepid", 8000, 1, 4, 50,
                SoldierTypes.MELEE, 0, "", buff);
        heroes.add(diveSepid);

        buff = new StunBuff(Kind.POSITIVE, 1, false);
        Hero simorgh = new Hero(302, "simorgh", 9000, 5, 4, 50,
                SoldierTypes.MELEE, 0, "", buff);
        heroes.add(simorgh);
        //todo:must have target area

    }

    private static void createSpellCards(ArrayList<SpellCard> spellCards, ArrayList<Buff> buffs) {
        buffs.add(new DisArmBuff(Kind.NEGATIVE, 0, true));
        Target target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_ENEMY);
        SpellCard totalDisarm = new SpellCard(201, "total Disarm", 1000, 0,
                "", buffs, target);
        spellCards.add(totalDisarm);

        buffs = new ArrayList<>();
        buffs.add(new AntiNegativeBuff(Kind.POSITIVE, 0, false));
        target = new Target(Type.AREA, 2, null);
        SpellCard areaDispel = new SpellCard(202, "Area Dispel", 1500, 2,
                "", buffs, target);
        spellCards.add(areaDispel);
        buffs = new ArrayList<>();

        buffs.add(new PowerBuff(Kind.POSITIVE, 1, false, 0, 2));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_FRIENDLY_SOLDIER);
        SpellCard empower = new SpellCard(203, "Empower", 250, 1,
                "", buffs, target);
        spellCards.add(empower);

        buffs = new ArrayList<>();
        buffs.add(new AttackBuff(Kind.NEGATIVE, 1, false, 4));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_ENEMY);
        SpellCard fireBall = new SpellCard(204, "Fire Ball", 400, 1,
                "", buffs, target);
        spellCards.add(fireBall);

        buffs = new ArrayList<>();
        buffs.add(new PowerBuff(Kind.POSITIVE, 1, false, 0, 4));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.FRIENDLY_HERO);
        SpellCard godStrength = new SpellCard(205, "God Strength", 450, 2,
                "", buffs, target);
        spellCards.add(godStrength);

        buffs = new ArrayList<>();
        buffs.add(new FireBuff(Kind.NEGATIVE, 2, false, 2));
        target = new Target(Type.AREA, 2, null);
        SpellCard hellFire = new SpellCard(206, "Hell Fire", 600, 3,
                "", buffs, target);
        spellCards.add(hellFire);

        buffs = new ArrayList<>();
        buffs.add(new AttackBuff(Kind.NEGATIVE, 1, false, 7));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ENEMY_HERO);
        SpellCard lightingBolt = new SpellCard(207, "Lighting Bolt", 1250, 2,
                "", buffs, target);
        spellCards.add(lightingBolt);

        buffs = new ArrayList<>();
        buffs.add(new PoisonBuff(Kind.NEGATIVE, 1, false, 1));
        target = new Target(Type.AREA, 3, null);
        SpellCard poisonLake = new SpellCard(208, "Poison Lake", 900, 5,
                "", buffs, target);
        spellCards.add(poisonLake);

        buffs = new ArrayList<>();
        buffs.add(new PowerBuff(Kind.POSITIVE, 3, false, 0, 4));
        buffs.add(new DisArmBuff(Kind.NEGATIVE, 3, false));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_FRIENDLY_SOLDIER);
        SpellCard madness = new SpellCard(209, "Madness", 650, 0,
                "", buffs, target);
        spellCards.add(madness);

        buffs = new ArrayList<>();
        buffs.add(new DisArmBuff(Kind.NEGATIVE, 1, false));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ALL_ENEMIES);
        SpellCard allDisarm = new SpellCard(210, "All Disarm", 2000, 9,
                "", buffs, target);
        spellCards.add(allDisarm);

        buffs = new ArrayList<>();
        buffs.add(new PoisonBuff(Kind.NEGATIVE, 4, false, 1));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ALL_ENEMIES);
        SpellCard allPoison = new SpellCard(211, "All Poison", 1500, 8,
                "", buffs, target);
        spellCards.add(allPoison);

        buffs = new ArrayList<>();
        buffs.add(new AntiNegativeBuff(Kind.POSITIVE, 1, false));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_SOLDIER);
        SpellCard dispel = new SpellCard(212, "Dispel", 2100, 0,
                "", buffs, target);
        spellCards.add(dispel);

        buffs = new ArrayList<>();
        buffs.add(new WeaknessBuff(Kind.NEGATIVE, 1, false, 6, 0));
        buffs.add(new HolyBuff(Kind.POSITIVE, 3, false, 2));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_FRIENDLY_SOLDIER);
        SpellCard healthWithProfit = new SpellCard(213, "Health with profit", 2250, 0,
                "", buffs, target);
        spellCards.add(hellFire);

        buffs = new ArrayList<>();
        buffs.add(new PowerBuff(Kind.POSITIVE, 1, false, 0, 6));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_FRIENDLY_SOLDIER);
        SpellCard powerUp = new SpellCard(214, "Power Up", 2500, 2,
                "", buffs, target);
        spellCards.add(powerUp);

        buffs = new ArrayList<>();
        buffs.add(new PowerBuff(Kind.POSITIVE, 0, true, 0, 2));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ALL_FRIENDLY_SOLDIERS);
        SpellCard allPower = new SpellCard(215, "All Power", 2000, 4,
                "", buffs, target);
        spellCards.add(allPower);

        buffs = new ArrayList<>();
        buffs.add(new AttackBuff(Kind.NEGATIVE, 1, false, 6));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ALL_ENEMIES_IN_A_COLUMN);
        SpellCard allAttack = new SpellCard(216, "All Attack", 1500, 4,
                "", buffs, target);
        spellCards.add(allAttack);

        buffs = new ArrayList<>();
        buffs.add(new WeaknessBuff(Kind.NEGATIVE, 1, false, 0, 4));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_ENEMY_MINION);
        SpellCard weakening = new SpellCard(217, "Weakening", 1000, 1,
                "", buffs, target);
        spellCards.add(weakening);

        buffs = new ArrayList<>();
        buffs.add(new WeaknessBuff(Kind.NEGATIVE, 1, false, 6, 0));
        buffs.add(new PowerBuff(Kind.POSITIVE, 1, false, 0, 8));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_FRIENDLY_MINION);
        SpellCard sacrifice = new SpellCard(218, "Sacrifice", 1600, 2,
                "", buffs, target);
        spellCards.add(sacrifice);

        buffs = new ArrayList<>();
        buffs.add(new KillBuff(Kind.NEGATIVE, 1, false));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.FRIENDLY_HERO);
        SpellCard kingsGuard = new SpellCard(219, "Kings Guard", 1750, 9,
                "", buffs, target);
        spellCards.add(kingsGuard);

        buffs = new ArrayList<>();
        buffs.add(new StunBuff(Kind.NEGATIVE, 2, false));
        target = new Target(Type.SOLDIER, 0, SoldierTargetType.ONE_ENEMY);
        SpellCard shock = new SpellCard(220, "Shock", 1200, 1,
                "", buffs, target);
        spellCards.add(shock);

    }

    private static void createItem(ArrayList<Item> items, ArrayList<Buff> buffs) {
        buffs.add(new HolyBuff(Kind.POSITIVE, 1, false, 12));
        Target target = new Target(Type.SOLDIER, 0, SoldierTargetType.FRIENDLY_HERO);
        Item namooseSepar = new Item(001, "Namoose Separ", 400,
                "", ItemTypes.USABLE, buffs, target);
        items.add(namooseSepar);
    }
}