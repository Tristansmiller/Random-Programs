function setup() {
  createCanvas(1000,1000);
  var chorus = new Tone.Chorus(2.5, .5, .5).toMaster();
  ampEnv = new Tone.AmplitudeEnvelope({
		"attack": .5,
		"decay": 3,
		"sustain": 2,
		"release": .5
	}).connect(chorus);
  osc = new Tone.Oscillator(500, "square").start();
  osc.volume.value=(-6);
  osc2 = new Tone.Oscillator(400,"sine").start();
  osc2.volume.value=(-6);
  osc.connect(ampEnv);
  osc2.connect(ampEnv);
  Tone.Transport.scheduleRepeat(beat1,"4n");
  Tone.Transport.scheduleRepeat(beat2,"2n");
  Tone.Transport.scheduleRepeat(beat3,"4n");
  Tone.Transport.loop=true;
  Tone.Transport.loop
}

var synth1 = new Tone.MembraneSynth().toMaster();
synth1.volume.value=(0);
var pingPong = new Tone.PingPongDelay("8n", 0.3).toMaster();
var synth2 = new Tone.MembraneSynth().connect(pingPong);
synth2.volume.value=(0);


var drawMode=false;
function changeCurrentColor(){
  selection=(mouseY-(mouseY%50))/50;
  switch(selection){
    case 0: stroke("#FF0000");colorChangeSound(-selection);break;
    case 1: stroke("#FF7F00");colorChangeSound(-selection);break;
    case 2: stroke("#FFFF00");colorChangeSound(-selection);break;
    case 3: stroke("#00FF00");colorChangeSound(-selection);break;
    case 4: stroke("#00FFFF");colorChangeSound(-selection);break;
    case 5: stroke("#0000FF");colorChangeSound(-selection);break;
    case 6: stroke("#FF00FF");colorChangeSound(-selection);break;
    case 7: stroke("#7F3F00");colorChangeSound(-selection);break;
    case 8: stroke("#FFFFFF");colorChangeSound(-selection);break;
    case 9: stroke("#000000");colorChangeSound(-selection);break;
    default: break;
  }
}
function colorChangeSound(pitchShift){
  var sampler = new Tone.Sampler("./colorchange.wav", function(){
	//repitch the sample down a half step
	sampler.triggerAttack(pitchShift);
}).toMaster();
}
function mousePressed(){
  if((mouseX>50 || mouseY>550)){
    drawMode=true;
    ampEnv.triggerAttack();
  }
  else if(mouseX<50 && mouseY<=550 && mouseY>500)
    resetPainting();
  else{
    changeCurrentColor();
    }
}
function mouseReleased(){
  drawMode=false;
  ampEnv.triggerRelease();
}

function drawPalette(){
  strokeWeight(0);
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
  var sampler = new Tone.Sampler("./reset.wav", function(){
  //repitch the sample down a half step
  sampler.triggerAttack();
  }).toMaster();
  Tone.Transport.bpm.value=80;
  Tone.Transport.stop(0);
  Tone.Transport.start(0);

}

function beat1(){
  synth1.triggerAttackRelease("C2", "4n");
}
function beat2(){
  synth2.triggerAttackRelease("E4", "8n");
}
function beat3(){
  synth2.triggerAttackRelease("G4","8n")
}
function draw() {
  Tone.Transport.start(0);
    if(drawMode){
      strokeWeight(10);
      osc.frequency.value=500-(mouseY/2);
      osc2.frequency.value=400-(mouseY/2);
      line(pmouseX,pmouseY,mouseX,mouseY);
      Tone.Transport.bpm.value+=.025;
    }
    drawPalette();

}
