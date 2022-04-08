package funny_balls;

import java.awt.*;

public class Background {
    // метод вычисляет значение красного, зеленого и синего цвета радуги в зависимости от заданного в секундах
    // временного периода (аргумент periodTime). Рассчет основан на деление периода на 6 частей, где значение
    // цветов максимально 255, минимально 0, линейно возрастет или линейно убывает.
    public static Color getColorOfRainbowByLineFunction(float totalTime, float periodTime) {
        int red;
        int green;
        int blue;
        float time = totalTime % periodTime;
        float part = periodTime / 6;
        float x = time % part;
        if (time <= part) {
            //первая часть - красный максимальный, зеленый возрастает, синий минимальный
            red = 255;
            green = (int) (x * 255 / part); //inc
            blue = 0;
        } else if (time <= 2 * part) {
            //вторая часть - красный убывает, зеленый максимальный, синий минимальный
            red = (int) (255 - 255 * x / part); //dec
            green = 255;
            blue = 0;
        } else if (time <= 3 * part) {
            //третья часть - красный минимальный, зеленый максимальный, синий возрастает
            red = 0;
            green = 255;
            blue = (int) (255 * x / part); //inc
        } else if (time <= 4 * part) {
            //четвертая часть - красный минимальный, зеленый убывает, синий максимальный
            red = 0;
            green = (int) (255 - 255 * x / part); //dec
            blue = 255;
        } else if (time <= 5 * part) {
            //пятая часть - красный возрастает, зеленый минимальный, синий максимальный
            red = (int) (255 * x / part); //inc
            green = 0;
            blue = 255;
        } else {
            //шестая часть - красный максимальный, зеленый минимальный, синий убывает
            red = 255;
            green = 0;
            blue = (int) (255 - 255 * x / part); //dec
        }
        return new Color(red, green, blue);
    }

    // метод также вычисляет цвета радуги, логика проще, колебание цветов по синусоидам, равномерно смещенных друг относительно друга,
    // однако, выглядит менее равномерно, остутствуют полноценные цвета радуги
    public static Color getColorOfRainbowByCosFunction(float totalTime, float periodTime) {
        double radian = (totalTime % periodTime) * 2 * Math.PI / periodTime;
        int red =  (int) (Math.cos(radian) * 255);
        if (red < 0) red = 0;
        int green = (int) (Math.cos(radian - 2 * Math.PI / 3) * 255);
        if (green < 0) green = 0;
        int blue = (int) (Math.cos(radian + 2 * Math.PI / 3) * 255);
        if (blue < 0) blue = 0;
        return new Color(red, green, blue);
    }
}