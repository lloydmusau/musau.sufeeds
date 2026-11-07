package com.interfaces_quiz;
    public class circle implements Measurement {
        private double radius;

        public circle(double radius) {
            this.radius = radius;
        }


        public double getPerimeter() {
            return 2 * 22/7 * radius;
        }


        public void showDetails() {
            System.out.println("Circle radius: " + radius);
            System.out.println("Circle perimeter: " + getPerimeter());
        }
    }

