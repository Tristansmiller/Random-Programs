function setup() {
  createCanvas(200,200);
}

function draw() {
  background(0,0,127);

  strokeWeight(2);
  stroke(255,255,255);
  
  fill(0,127,0);
  ellipse(100,100,100,100);

  fill(255,0,0);
  beginShape();
  vertex(100,49);
  vertex(115,80);
  vertex(150,80);
  vertex(120,100);
  vertex(130,140);
  vertex(100,115);
  vertex(70,140);
  vertex(80,100);
  vertex(50,80);
  vertex(85,80);
  endShape(CLOSE);

}
