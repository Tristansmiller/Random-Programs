function setup() {
  createCanvas(2000,2000);
}


var ticks =0;
var drawing = false;
var redVal =255;
var blueVal =255;
var greenVal =255;
var x = 1000;
var y = 1000;
var xSpeed = 10;
var ySpeed = 10;

var xAdjustment = 0;
var yAdjustment = 0;
var pause = false;
var drawType=round(random(.5,5.5));
function mousePressed(){
  drawing = true;
}

function mouseReleased(){
  drawing= false;
}

function keyReleased(){
  if(keyCode == SHIFT)
    pause= !pause;
}

function colorAdjust(){
  var colorChoice = round(random(.5,3.5));
  var signChoice = round(random(0,1));
  if(colorChoice==1){
    if((redVal<10 || signChoice==1) && redVal<240 )
      var redAdjust = round(random(0,5));
    else {
      var redAdjust = -round(random(0,5));
    }
    var greenAdjust = 0;
    var blueAdjust = 0;
  }else if(colorChoice==2){
    var redAdjust = 0;
    if((greenVal<10 || signChoice==1) && greenVal<240)
      var greenAdjust = round(random(0,5));
    else{
      var greenAdjust = -round(random(0,5));
    }
    var blueAdjust = 0;
  }else{
    var redAdjust = 0;
    var greenAdjust = 0;
    if((blueVal<10 || signChoice==1) && blueVal<240)
      var blueAdjust = round(random(0,5));
    else{
      var blueAdjust = -round(random(0,5));
    }
  }
  redVal+=redAdjust;
  greenVal+=greenAdjust;
  blueVal+=blueAdjust;
}

function speedAdjust(){
  var directionChoice = round(random(0,1));
  var signChoice = round(random(0,1));
  if(directionChoice==0){
    if((xSpeed<2 ||signChoice == 0) && xSpeed<8){
      var xSpeedAdjust = round(random(0,2));
    }
    else{
      var xSpeedAdjust = -round(random(0,2));
    }
    var ySpeedAdjust = 0;
  }else{
    if((ySpeed<2 || signChoice==0) && ySpeed<8){
      var ySpeedAdjust = round(random(0,2));
    }
    else{
      var ySpeedAdjust = -round(random(0,2));
    }
    var xSpeedAdjust = 0;
  }

  xSpeed+=xSpeedAdjust;
  ySpeed+=ySpeedAdjust;
}

var strokeAdjust=1
var strokeWidth=20

function draw() {
  noStroke();
  //background(0,0,0);
  //text("( "+int(mouseX)+" , "+int(mouseY)+" )",mouseX,mouseY);
  if(!ticks>0){
    fill(0,0,0);
    rect(0,0,2000,2000);
  }
  if(!pause){
     colorAdjust();
     //speedAdjust();
     if(drawing){
       xAdjustment = ((mouseX-x)/(dist(x,y,mouseX,mouseY)))*xSpeed;
       yAdjustment = ((mouseY-y)/(dist(x,y,mouseX,mouseY)))*ySpeed;
       ellipse(x,y,25,25);
       x+=xAdjustment;
       y+=yAdjustment;
       ticks+=1;
    }
    else{
      stroke(redVal,greenVal,blueVal);
      strokeWeight(25);
      if(ticks%10==0 || (x<100 || y<100 || x>1900 || y>1900)){
        print(ticks+" "+ticks%20);
        drawType=round(random(.5,5.5));
        if(x<100 || y<100){
          var randomAdjust = random(0,1);
        }else if(x>1900 || y>1900) {
          var randomAdjust = random(-1, 0);
        }else{
          var randomAdjust = random(-1,1);
        }
        if(drawType==1){
            xAdjustment = round(randomAdjust)*xSpeed;
            yAdjustment = 0;
        }else if(drawType==2){
            yAdjustment = round(randomAdjust)*xSpeed;
            xAdjustment = 0;
        }else if(drawType==3){
            xAdjustment = round(randomAdjust)*xSpeed;
            yAdjustment = round(randomAdjust)*ySpeed;
        }else if(drawType==4){
            xAdjustment = round(randomAdjust)*xSpeed;
            yAdjustment = -round(randomAdjust)*ySpeed;
        }else{
            xAdjustment = -round(randomAdjust)*xSpeed;
            yAdjustment = round(randomAdjust)*ySpeed;
        }
      }
      line(x,y,x+xAdjustment,y+yAdjustment);
      x+=xAdjustment;
      y+=yAdjustment;
      ticks+=1;

    }
  }

}
