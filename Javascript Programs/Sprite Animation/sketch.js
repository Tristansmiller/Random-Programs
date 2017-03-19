function setup() {
  createCanvas(900,900);
}
var numSprites = 12;
var sprites = new Array(12);
var spriteURLS = new Array("SpelunkyGuy.png","Green.png","Yeti.png");

function preload(){
  for(var k=0;k<numSprites;k++)
    sprites[k]=new Sprite(random(15,885),random(15,885),spriteURLS[k%3]);
}

function draw() {
  background(0,0,0);
  for(var k=0;k<numSprites;k++){
    sprites[k].draw();
  }

}

function keyPressed(){
  if(keyCode === LEFT_ARROW){
    for(var k=0;k<numSprites;k++){
      sprites[k].xMove(-1);
    }
  }
  if(keyCode === RIGHT_ARROW){
    for(var k=0;k<numSprites;k++){
          sprites[k].xMove(1);
        }
  }
  if(keyCode === UP_ARROW){
    for(var k=0;k<numSprites;k++){
          sprites[k].yMove(-1);
        }
  }
  if(keyCode === DOWN_ARROW){
    for(var k=0;k<numSprites;k++){
          sprites[k].yMove(1);
        }
  }
}

function keyReleased(){
  if(keyCode === LEFT_ARROW){
    for(var k=0;k<numSprites;k++){
      sprites[k].xMove(0);
    }
  }
  if(keyCode === RIGHT_ARROW){
    for(var k=0;k<numSprites;k++){
          sprites[k].xMove(0);
        }
  }
  if(keyCode === UP_ARROW){
    for(var k=0;k<numSprites;k++){
          sprites[k].yMove(0);
        }
  }
  if(keyCode === DOWN_ARROW){
    for(var k=0;k<numSprites;k++){
          sprites[k].yMove(0);
        }
  }
}
function Sprite(xPos,yPos,imgURL){
  var spriteSheet = loadImage(imgURL);
  var x = xPos;
  var xInc = 0;
  var y = yPos;
  var yInc = 0;
  var speed = 6;
  var frame=0;
  var ticks=0;
  var direction = 1;
  var yeti = (imgURL=="Yeti.png");

  var frameMax = 8;
  if(yeti) frameMax=6;

  this.draw = function(){
    this.adjustPosition()
    push();
    translate(x,y);
    scale(direction,1);
    imageMode(CENTER);
    if(yeti){
      switch(frame){
        case 0: image(spriteSheet,0,0,80,80,0,10,160,160); break;
        case 1: image(spriteSheet,0,0,80,80,0,170,160,160); break;
        case 2: image(spriteSheet,0,0,80,80,160,170,160,160); break;
        case 3: image(spriteSheet,0,0,80,80,320,170,160,160); break;
        case 4: image(spriteSheet,0,0,80,80,480,170,160,160); break;
        case 5: image(spriteSheet,0,0,80,80,640,170,160,160); break;
        case 6: image(spriteSheet,0,0,80,80,800,170,160,160); break;
        default: image(spriteSheet,0,0,80,80,0,0,80,80); break;
      }
    }
    else{
      switch(frame){
        case 0: image(spriteSheet,0,0,80,80,0,0,80,80); break;
        case 1: image(spriteSheet,0,0,80,80,80,0,80,80); break;
        case 2: image(spriteSheet,0,0,80,80,160,0,80,80); break;
        case 3: image(spriteSheet,0,0,80,80,240,0,80,80); break;
        case 4: image(spriteSheet,0,0,80,80,320,0,80,80); break;
        case 5: image(spriteSheet,0,0,80,80,400,0,80,80); break;
        case 6: image(spriteSheet,0,0,80,80,480,0,80,80); break;
        case 7: image(spriteSheet,0,0,80,80,560,0,80,80); break;
        case 8: image(spriteSheet,0,0,80,80,640,0,80,80); break;
        default: image(spriteSheet,0,0,80,80,0,0,80,80); break;
      }
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
  }
  this.adjustPosition = function(){
    x+=xInc;
    y+=yInc;
    if(xInc>0){
      direction = 1;
    }
    if(xInc<0){
      direction = -1;
    }
  }
  this.xMove = function(increment){
    xInc=increment;
  }
  this.yMove = function(increment){
    yInc=increment;
  }
}
