function setup() {
  createCanvas(5000,5000);
}
var drawMode=false;
function colorCheck(){
  stroke(document.getElementById("color").value);
}
function changeCurrentColor(color){
  switch(color){
    case 0: document.getElementById("color").value="#FF0000";break;
    case 1: document.getElementById("color").value="#FF7F00";break;
    case 2: document.getElementById("color").value="#FFFF00";break;
    case 3: document.getElementById("color").value="#00FF00";break;
    case 4: document.getElementById("color").value="#00FFFF";break;
    case 5: document.getElementById("color").value="#0000FF";break;
    case 6: document.getElementById("color").value="#FF00FF";break;
    case 7: document.getElementById("color").value="#7F3F00";break;
    case 8: document.getElementById("color").value="#FFFFFF";break;
    case 9: document.getElementById("color").value="#000000";break;
    default: break;
  }
}
function mousePressed(){
  if((mouseX>50 || mouseY>550)){
    drawMode=true;
    colorCheck();
  }
  else if(mouseX<50 && mouseY<=550 && mouseY>500)
    resetPainting();
  else{
    changeCurrentColor((mouseY-(mouseY%50))/50);
    colorCheck();
    }
}

function mouseReleased(){
  drawMode=false;
}

function drawPalette(){
  noStroke();
  fill(255,0,0);//red palette #0
  rect(0,0,50,50);

  fill(255,127,0);//orange palette #1
  rect(0,50,50,50);

  fill(255,255,0);//yellow palette #2
  rect(0,100,50,50);

  fill(0,255,0);//green palette #3
  rect(0,150,50,50);

  fill(0,255,255);//cyan palette #4
  rect(0,200,50,50);

  fill(0,0,255);//blue palette #5
  rect(0,250,50,50);

  fill(255,0,255);//magenta palette #6
  rect(0,300,50,50);

  fill(127,63,0);//brown palette #7
  rect(0,350,50,50);

  fill(255,255,255);//white palette #8
  rect(0,400,50,50);

  fill(0,0,0);//black palette #9
  rect(0,450,50,50);

  text("Reset",0,520,50,50);

}
function resetPainting(){
  background(255,255,255);
}
function draw() {
    if(drawMode){
      strokeWeight(10);
      colorCheck();
      line(pmouseX,pmouseY,mouseX,mouseY);
    }
    drawPalette();


}
