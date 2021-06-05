let oilVolume = document.getElementById('oilvolume');

oilVolume.addEventListener("change", calc);
oilVolume.addEventListener("change", valid);

let oilFee = document.getElementById('oilfee');
let oilCost = document.getElementById('oilcost');

function calc() {
    let oilVolumeInt = parseInt(oilVolume.value, 10);

    let getOilVolume = (oilVolumeInt*0.1).toFixed(0);
    if(!isNaN(getOilVolume)){
        oilFee.textContent = getOilVolume;
    } else {
        oilFee.textContent = "";
    }

    let getOilVolume2 = (oilVolumeInt*(28.1+0.1)).toFixed(0);
    if(!isNaN(getOilVolume)){
        oilCost.textContent = getOilVolume2;
    } else {
        oilCost.textContent = "";
    }

    document.getElementById('oilfee_submit').value = getOilVolume;
    document.getElementById('oilcost_submit').value = getOilVolume2;
}


function valid() {
    let oilVolumeInt = parseInt(oilVolume.value, 10);

    if(oilVolumeInt>5000) {
        alert("最大數量不得高於5000公升");
    } else if(oilVolumeInt<100) {
        alert("最小數量不得低於100公升");
    }
}