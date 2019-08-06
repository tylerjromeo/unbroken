package org.romeo.unbroken.state

import scalaz.State

case class GameState(resources: Resources,
                     effort: Effort,
                     deckState: DeckState,
                     activeWeapon: Weapon,
                     character: PlayerCharacter,
                     skills: Seq[SkillCard],
                     conditions: Seq[Condition],
                     activeMonster: Option[Monster],
                     phase: Phase,
                     currentLevel: Level,
                     timeRemaining: Int,
                     difficulty: Difficulty)

case class Resources(cunning: Int, food: Int, wood: Int, metal: Int, treasure: Int)

case class Effort(small: Int, medium: Int, large: Int)

case class DeckState(encounterDeck: Seq[EncounterCard],
                     encounterDiscard: Seq[EncounterCard],
                     skillDeck: Seq[SkillCard],
                     skillDiscard: Seq[SkillCard])

abstract class EncounterCard(name: String) {

}

case class Weapon(name: String, weaponType: WeaponType, attacks: Seq[WeaponAttack])

abstract class WeaponAttack(cost: Cost) {
  def weaponEffect: State[GameState, Monster]
}

case class PlayerCharacter(name: String,
                           maxAbilityUses: Int,
                           abilityUsesRemaining: Int,
                           abilities: Seq[CharacterAbility])

abstract class CharacterAbility(cost: Cost) {
  type T

  def getReturnType: Class[T] = Class[T]

  def abilityEffect: State[GameState, T]
}

case class SkillCard(name: String)

case class Condition(name: String)

case class Monster(name: String)

case class Phase(name: String, nextPhaseChoices: Seq[Phase])

case class Cost(small: Int, medium: Int, large: Int, cunning: Int, food: Int, wood: Int, metal: Int, treasure: Int)

sealed trait Level

case object Level1 extends Level

case object Level2 extends Level

case object Level3 extends Level

case object Level4 extends Level

sealed trait Difficulty

case object Easy extends Difficulty

case object Medium extends Difficulty

case object Hard extends Difficulty

sealed trait WeaponType

case object BareHands

case object Basic

case object Advanced

