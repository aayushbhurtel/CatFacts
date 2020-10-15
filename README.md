# CatFacts
CatFacts uses RetroFit2 to pull cat facts from an api and glide library to pull cat images which is displayed in the only activity i.e. MainActivity. 
User can click on "next" button to load more cat facts and images into the MainActivity.
This application is inspired from youtube content creater and android developer with youtube channel name "Stevdza-San".

import turtle
import sys
  
#sys.setExecutionLimit(60000) # 60 seconds
def draw_rectangle(turt, width, height, color):
    turt.pencolor(color)
    turt.fillcolor(color)
    
    turt.up()
    x = - width / 2
    y = height / 2
    turt.goto(x,y)
    turt.down()

    turt.begin_fill()
    # Draw the top
    turt.setheading(0)
    turt.forward(width)
    # Draw the right side
    turt.right(90)
    turt.forward(height)
    # Draw the bottom
    turt.right(90)
    turt.forward(width)
    # Draw the left side
    turt.right(90)
    turt.forward(height)
    turt.end_fill()

turt = turtle.Turtle()
window = turtle.Screen()

# taking input for the height of the rectangle
prompt1 = ""This program will draw a concentric rectangles using four different colors.
Please specify the height of the rectangle ""
height = float(input(prompt1))

# taking input for the width of the rectangle
prompt2 = ""Please specify the height of the rectangle ""
width = float(input(prompt2)) 
  
window.setup(width,height)
window.bgcolor("blue")

# declaring color
color = turt.color()
newColors = ["red", "yellow", "green"]
for color in newColors:
    width = width / 2
    height = height / 2
    draw_rectangle(turt,width,height,color)
    
