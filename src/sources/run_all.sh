#!/usr/bin/env bash
set -euo pipefail

echo "[1/2] Compiling all Java files..."
javac \
  part1/part1_1/NumberClassifier.java \
  part1/part1_2/GradeChecker.java \
  part1/part1_3/ObjectDescriber.java \
  part2/part2_1/MultiplicationTable.java \
  part2/part2_2/Fibonacci.java \
  part2/part2_3/StringProcessor.java \
  part2/part2_4/LoopControl.java \
  part3/part3_1/interfaces/Electric.java \
  part3/part3_1/model/Vehicle.java \
  part3/part3_1/model/Car.java \
  part3/part3_1/model/Truck.java \
  part3/part3_1/model/ElectricCar.java \
  part3/part3_1/demo/VehicleDemo.java \
  part3/part3_2/BankAccount.java \
  part3/part3_2/BankAccountDemo.java \
  part4/part4_1/DiscountCalculator.java \
  part4/part4_2/Shape.java \
  part4/part4_2/Circle.java \
  part4/part4_2/Rectangle.java \
  part4/part4_2/Square.java \
  part4/part4_2/ShapeTest.java \
  part5/part5_1/Trainable.java \
  part5/part5_1/Animal.java \
  part5/part5_1/Predator.java \
  part5/part5_1/Herbivore.java \
  part5/part5_1/Lion.java \
  part5/part5_1/Elephant.java \
  part5/part5_1/Zoo.java \
  part5/part5_1/ZooDemo.java

echo "[2/2] Running demo classes..."
classes=(
  "part1.part1_1.NumberClassifier"
  "part1.part1_2.GradeChecker"
  "part1.part1_3.ObjectDescriber"
  "part2.part2_1.MultiplicationTable"
  "part2.part2_2.Fibonacci"
  "part2.part2_3.StringProcessor"
  "part2.part2_4.LoopControl"
  "part3.part3_1.demo.VehicleDemo"
  "part3.part3_2.BankAccountDemo"
  "part4.part4_1.DiscountCalculator"
  "part4.part4_2.ShapeTest"
  "part5.part5_1.ZooDemo"
)

for cls in "${classes[@]}"; do
  echo
  echo "===== Running ${cls} ====="
  java "${cls}"
done

echo
echo "Done."
