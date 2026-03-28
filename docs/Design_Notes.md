# Design Notes

## 1. Using `ArrayList` instead of `Array`
For storing Students, Courses, and Enrollments, I decided to use `ArrayList` instead of a standard Java array. A standard array has a fixed size, which means I'd have to know exactly how many elements to store upfront, or I would be forced to write extra logic to manually resize it constantly. `ArrayList` handles dynamic resizing automatically underneath, making it super easy to just use `.add()` and `.remove()` as the app's data grows.

## 2. Use of Static Members
I used static variables mostly for the `IdGenerator` utility class (like `private static int studentIdCounter`). 
- **Why?** Since IDs need to increment sequentially across the entire application, making these counters `static` means they belong to the class itself, rather than individual instances. This guarantees there is only one shared, global counter for students, one for courses, and one for enrollments.

## 3. Use of Inheritance
I created a `Person` base class that holds common attributes such as `id`, `firstName`, `lastName`, and `email`.
- **Inheritance Example:** I then made both `Student` and `Trainer` extend the `Person` class.
- **Benefits:** By leveraging inheritance, I completely avoided duplicating those common fields and their associated getters/setters in the child classes. I also took advantage of polymorphism by overriding the `getDisplayName()` method in `Student` and `Trainer` to append their specific roles (e.g., adding the batch or specialization). This kept the code very DRY (Don't Repeat Yourself) and makes adding new types of people way simpler in the future!
