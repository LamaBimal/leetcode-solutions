package com.learning.principles;

/**
 * Composition over Inheritance
 *
 * Favor composing behavior via interfaces/delegation over deep inheritance chains.
 *
 * BAD: Bird → FlyingBird → SwimmingFlyingBird — hierarchy explodes with combinations.
 * GOOD: Compose a Bird with optional Flyable / Swimmable behaviors injected at construction.
 */
public class CompositionOverInheritance {

    public interface Flyable  { String fly(); }
    public interface Swimmable { String swim(); }

    // Concrete behaviors
    public static class CanFly  implements Flyable  { public String fly()  { return "flying"; } }
    public static class CanSwim implements Swimmable { public String swim() { return "swimming"; } }

    // Bird is composed of optional behaviors — no inheritance needed
    public static class Bird {
        private final String name;
        private final Flyable  flyable;
        private final Swimmable swimmable;

        public Bird(String name, Flyable flyable, Swimmable swimmable) {
            this.name      = name;
            this.flyable   = flyable;
            this.swimmable = swimmable;
        }

        public String describe() {
            StringBuilder sb = new StringBuilder(name + " can:");
            if (flyable  != null) sb.append(" ").append(flyable.fly());
            if (swimmable != null) sb.append(" ").append(swimmable.swim());
            return sb.toString();
        }
    }

    // Eagle flies, doesn't swim
    public static Bird eagle() {
        return new Bird("Eagle", new CanFly(), null);
    }

    // Duck flies and swims
    public static Bird duck() {
        return new Bird("Duck", new CanFly(), new CanSwim());
    }

    // Penguin swims, doesn't fly
    public static Bird penguin() {
        return new Bird("Penguin", null, new CanSwim());
    }
}
