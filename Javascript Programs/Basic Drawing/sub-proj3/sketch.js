function setup() {
  createCanvas(200,100);
}

function draw() {
  background(0,0,0);
  noStroke();
  fill(255,255,0);
  arc(50,50,80,80,radians(-140),radians(140));

  fill(255,0,0);
  arc(150,50,80,80,radians(-180),radians(0));
  rect(110,50,80,40);

  fill(255,255,255);
  ellipse(130,50,25,25);
  ellipse(170,50,25,25);

  fill(0,0,255);
  ellipse(130,50,13,13);
  ellipse(170,50,13,13);


}
