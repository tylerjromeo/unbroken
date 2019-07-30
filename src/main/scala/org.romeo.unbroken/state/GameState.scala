package org.romeo.unbroken.state

import org.romeo.unbroken.state.Level.Level

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

case class Weapon()

case class PlayerCharacter()

case class Skill()

case class Condition()

case class Monster()

case class Phase()

object Level extends Enumeration {
  type Level = Value
  val Level1, Level2, Level3, Level4 = Value
}