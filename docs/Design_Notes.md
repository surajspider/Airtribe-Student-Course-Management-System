# Design Notes

## 1. Using `ArrayList` instead of `Array`
For storing Students, Courses, and Enrollments, we used an `ArrayList`. An standard array in Java has a fixed size, meaning we'd have to know exactly how many elements we want to store upfront or manually resize it (which requires creating a new array and copying elements over). `ArrayList` handles dynamic resizing automatically underneath, allowing us to seamlessly add and remove elements as needed, which is ideal for an application managing unpredictable amounts of data.

## 2. Use of Static Members
Static variables and methods were used primarily in the `IdGenerator` utility class (`private static int studentIdCounter`, etc.). 
- **Why?** Since IDs must increment sequentially across the entire application regardless of which service creates them, binding the counters to the class level (instead of individual objects) ensures there is only one shared counter for students, one for courses, and one for enrollments.

## 3. Use of Inheritance
We introduced a `Person` base class that holds common attributes such as `id`, `firstName`, `lastName`, and `email`.
- **Inheritance Example:** The `Student` class extends `Person`.
- **Benefits:** By leveraging inheritance, we avoided duplicating those common fields and their associated getters/setters in the `Student` class. We also reused the base logic in `getDisplayName()`, overriding it in `Student` to append standard behavior (`Student - [batch]`), applying polymorphism. This keeps our code DRY (Don't Repeat Yourself) and makes future extensions (e.g., adding a `Trainer` class) much simpler.
