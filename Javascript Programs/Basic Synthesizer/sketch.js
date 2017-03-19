
var ampEnv;
var synth1;
var synth2;
var imgOn;
var imgOff;
function preload(){
	imgOn = loadImage("assets/imgOn.png");
	imgOff = loadImage("assets/imgOff.png");
}

function setup() {
	createCanvas(600,400);

	var phaser = new Tone.Phaser({
	"frequency" : 15,
	"octaves" : 5,
	"baseFrequency" : 1000
	}).toMaster();
	var pingPong = new Tone.PingPongDelay("4n", 0.2).connect(phaser);

	synth1 = new Tone.PolySynth(5,Tone.Synth).connect(pingPong);
	synth1.set({
	"filter" : {
		"type" : "highpass"
	},
	"envelope" : {
		"attack": 3,
		"decay": 2,
		"sustain": 4,
		"release": 1
	}

});

var autoPanner = new Tone.AutoPanner("4n").toMaster().start();
synth2 = new Tone.PolySynth(5,Tone.Synth).connect(autoPanner);
synth2.set({
"filter" : {
	"type" : "highpass"
},
"envelope" : {
	"attack" : 2,
	"decay" : 5,
	"sustain" : 4,
	"release" : 1
}

});

}

function mousePressed() {
	synth1.triggerAttack(["C4", "G4", "C5"], ["2n", "10n", "20n"]);
}

function mouseReleased() {
	synth1.releaseAll();
	synth2.triggerAttackRelease(["C5","E5"],"2n")

}


function draw() {
	background(0,0,0);
	if(mouseIsPressed){
		image(imgOn,0,0);
	}
	else{
		image(imgOff,0,0);
	}


}
