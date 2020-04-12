package me.gorbuvla.domain.util

// General extension on [Boolean]

infix fun Boolean.implies(other: Boolean) = !this || other