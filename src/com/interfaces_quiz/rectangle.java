package com.interfaces_quiz;


    public class rectangle implements Measurement {
        private double width;
        private double height;

        public rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        @Override
        public double getPerimeter() {
            return 2 * (width + height);
        }

        public void showDetails() {
            System.out.println("Rectangle width: " + width);
            System.out.println("Rectangle height: " + height);
            System.out.println("Rectangle perimeter: " + getPerimeter());
        }
    }

