package org.romeo.unbroken.state

import org.romeo.unbroken.state.Level.Level
import org.romeo.unbroken.state.WeaponType.WeaponType

import scalaz.State

case class GameState(resources: Resources,
                     effort: Effort,
                     deckState: DeckState,
                     activeWeapon: Weapon,
                     character: PlayerCharacter,
                     skills: Seq[Skill],
                     conditions: Seq[Condition],
                     activeMonster: Option[Monster],
                     phase: Phase,
                     currentLevel: Level,
                     timeRemaining: Int)

case class Resources(cunning: Int, food: Int, wood: Int, metal: Int, treasure: Int)

case class Effort(small: Int, medium: Int, large: Int)

case class DeckState()

case class Weapon(name: String, weaponType: WeaponType, attacks: Seq[WeaponAttack])

object WeaponType extends Enumeration {
  type WeaponType = Value
  val BareHands, Basic, Advanced = Value
}

abstract class WeaponAttack(cost: Cost) {
  def weaponEffect: State[GameState, Monster]
}

case class PlayerCharacter(name: String, maxAbilityUses: Int, abilityUsesRemaining: Int, abilities: Seq[CharacterAbility])

abstract class CharacterAbility(cost: Cost) {
  type T
  def getReturnType: Class[T] = Class[T]
  def abilityEffect: State[GameState, T]
}

case class Skill()

case class Condition()

case class Monster()

case class Phase()

case class Cost()

object Level extends Enumeration {
  type Level = Value
  val Level1, Level2, Level3, Level4 = Value
}