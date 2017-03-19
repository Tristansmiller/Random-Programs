function setup() {
  createCanvas(1000,1000);

}
var numBugs = 12;
var bugs = new Array(12);
var startTime = (new Date()).getTime();
var numBugsSquished = 0;
var gameOver=false;
var collisionTestOn=false;
var highScore = 0;

function preload(){
  for(var k=0;k<numBugs;k++)
    bugs[k]=new Bug(random(15,885),random(15,885));
}

function draw() {
  if(10-(((new Date()).getTime()-startTime)/1000)<=0){
    background(255,255,255);
    for(var k=0;k<numBugs;k++){
      bugs[k].draw();
    }
    updateDisplay();
  }
  if(40-(((new Date()).getTime()-startTime)/1000)<=0){
    numBugsSquished=0;
    startTime=(new Date()).getTime();
    preload();
  }
}

function updateDisplay(){
  var currScoreElement = document.getElementById("currScore");
  currScoreElement.innerHTML="Score: "+numBugsSquished;
  var highScoreElement = document.getElementById("highScore");
  if(numBugsSquished>highScore){
    highScoreElement.innerHTML="High Score: "+numBugsSquished;
    highScore=numBugsSquished;
  }
  var timerElement = document.getElementById("time");
  var currTime = (new Date()).getTime()-startTime;
  var currTime = (currTime/1000);
  timerElement.innerHTML="Time Left: " + (round(40-currTime));

}

function checkCollisions(mX,mY){
  for(var k=0;k<bugs.length;k++){
    console.log("checking collision");
    if(bugs[k].collisionCheck(mX,mY))
      numBugsSquished++;
    }
  for(var k=0;k<bugs.length;k++){
    bugs[k].increaseSpeed(numBugsSquished);
  }
}
function mouseClicked(){
  checkCollisions(mouseX,mouseY);
}

function Bug(xPos,yPos){
  var spriteSheet = loadImage("./assets/bugSprite.png");
  var x = xPos;
  var xInc = 0;
  var y = yPos;
  var yInc = 0;
  var speed = 3;
  var frame =0;
  var ticks=0;
  var direction = 1;
  var frameMax = 3;
  var squished = false;

  this.draw = function(){
    if(!squished){
      if(ticks%20==0){
        this.adjustPositionChange();
      }
      this.adjustPosition();
    }
    push();
    translate(x,y);
    scale(direction,1);
    imageMode(CENTER);
    switch(frame){
      case 0: image(spriteSheet,0,0,50,50,50,0,50,50); break;
      case 1: image(spriteSheet,0,0,50,50,100,0,50,50); break;
      case 2: image(spriteSheet,0,0,50,50,50,0,50,50); break;
      case 3: image(spriteSheet,0,0,50,50,0,0,50,50); break;
      case 4: image(spriteSheet,0,0,50,50,150,0,50,50)
      default: image(spriteSheet,0,0,50,50,0,50,50,50); break;
    }
    pop();
    if(ticks%speed==0){
      if(frame==frameMax){
        frame=0
        ticks=-1;
      }
      else {
        frame++;
      }
    }
    if(xInc!=0 || yInc!=0){
      ticks++;
    }
    if(xInc==0 && yInc==0){
      frame=0;
      ticks=0;
    }
    if(squished){
      frame=4;
    }

  }
  this.increaseSpeed = function(change){
    this.speed=change+3;
  }
  this.collisionCheck = function(mX,mY){
    if(mX>=x && mX<=(x+50) && mY>=y && mY<=(y+50)){
      this.squish();
      return true;
    }
    return false;
  }
  this.squish = function(){
    squished=true;
  }
  this.adjustPositionChange = function(){
    xInc=round(random(-1.5,1.5))*speed;
    yInc=round(random(-1.5,1.5))*speed;
    if((xInc>=1 && x>800) || (xInc<=-1 && x<200) ){
      xInc*=-1;
    }
    if((yInc>=1 && y>800) || (yInc<=-1 && y<200) ){
      yInc*=-1;
    }
    if(xInc>0){
      direction=-1;
    }
    else if(xInc<0){
      direction=1;
    }
  }
  this.adjustPosition = function(){
      x+=xInc;
      y+=yInc;

  }
}
