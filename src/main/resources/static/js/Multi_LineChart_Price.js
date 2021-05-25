let csvData = "https://raw.githubusercontent.com/k22949706/Project-Oil/master/BRF_CPCDiesel_MonthlyAveragePrice_All.csv";

function onClick92() {
    let btn92 = document.getElementById("oil92");
    btn92.style.border="2px #000000 solid"
    let btn95 = document.getElementById("oil95");
    btn95.style.border="1.5px #3079ED solid"
    let btn98 = document.getElementById("oil98");
    btn98.style.border="1.5px #3079ED solid"
    let btnDiesel = document.getElementById("oilDiesel");
    btnDiesel.style.border="1.5px #3079ED solid"

    oiltype = "oil92";
    oilrect = "92無鉛"
    csvData = "https://raw.githubusercontent.com/k22949706/Project-Oil/master/BRF_CPC92_MonthlyAveragePrice_All.csv";
    d3.csv(csvData, function (data) {
        update(data, oiltype, oilrect);
    });
}
function onClick95() {
    let btn95 = document.getElementById("oil95");
    btn95.style.border="2px #000000 solid"
    let btn92 = document.getElementById("oil92");
    btn92.style.border="1.5px #3079ED solid"
    let btn98 = document.getElementById("oil98");
    btn98.style.border="1.5px #3079ED solid"
    let btnDiesel = document.getElementById("oilDiesel");
    btnDiesel.style.border="1.5px #3079ED solid"

    oiltype = "oil95";
    oilrect = "95無鉛"
    csvData = "https://raw.githubusercontent.com/k22949706/Project-Oil/master/BRF_CPC95_MonthlyAveragePrice_All.csv";
    d3.csv(csvData, function (data) {
        update(data, oiltype, oilrect);
    });
}
function onClick98() {
    let btn98 = document.getElementById("oil98");
    btn98.style.border="2px #000000 solid"
    let btn92 = document.getElementById("oil92");
    btn92.style.border="1.5px #3079ED solid"
    let btn95 = document.getElementById("oil95");
    btn95.style.border="1.5px #3079ED solid"
    let btnDiesel = document.getElementById("oilDiesel");
    btnDiesel.style.border="1.5px #3079ED solid"

    oiltype = "oil98";
    oilrect = "98無鉛"
    csvData = "https://raw.githubusercontent.com/k22949706/Project-Oil/master/BRF_CPC98_MonthlyAveragePrice_All.csv";
    d3.csv(csvData, function (data) {
        update(data, oiltype, oilrect);
    });
}
function onClickDiesel() {
    let btnDiesel = document.getElementById("oilDiesel");
    btnDiesel.style.border="2px #000000 solid"
    let btn92 = document.getElementById("oil92");
    btn92.style.border="1.5px #3079ED solid"
    let btn95 = document.getElementById("oil95");
    btn95.style.border="1.5px #3079ED solid"
    let btn98 = document.getElementById("oil98");
    btn98.style.border="1.5px #3079ED solid"

    oiltype = "oilDiesel";
    oilrect = "超級柴油"
    csvData = "https://raw.githubusercontent.com/k22949706/Project-Oil/master/BRF_CPCDiesel_MonthlyAveragePrice_All.csv";
    d3.csv(csvData, function (data) {
        update(data, oiltype, oilrect);
    });
}

// set the dimensions and margins of the graph
let margin = { top: 50, right: 50, bottom: 50, left: 50 },
    width = 800 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

// append the svg object to the body of the page
let svg = d3.select("main")
    .append("svg")
    .attr('class', 'est')
    .attr("viewBox", "0 0 800 400")
    .append("g")
    .attr("transform",
        "translate(" + margin.left + "," + margin.top + ")");

let x = d3.scaleTime()
    .range([0, width]);

let y = d3.scaleLinear()
    .range([height, 0]);

//設定線段資料
let line = d3
    .line()
    .curve(d3.curveBasis)
    .x(function (d) {
        return x(d.date);
    })
    .y(function (d) {
        return y(d.prices);
    });

let parseDate = d3.timeParse("%Y/%m");

let color = d3.scaleOrdinal(d3.schemeCategory10);

function update(data, oiltype) {
    color.domain(d3.keys(data[0]).filter(function (key) {
        return key !== "date";
    }));

    data.forEach(function (d) {
        d.date = parseDate(d.date);
    });

    //mapping資料
    oils = color.domain().map(function (name) {
        return {
            name: name,
            values: data.map(function (d) {
                return {
                    date: d.date,
                    prices: +d[name]
                };
            })
        };
    });

    //座標軸設定
    x.domain(d3.extent(data, function (d) {
        return d.date;
    }));
    y.domain([
        d3.min(oils, function (c) {
            return d3.min(c.values, function (v) {
                return v.prices;
            });
        }),
        d3.max(oils, function (c) {
            return d3.max(c.values, function (v) {
                return v.prices;
            });
        })
    ]);
    
    // Add the X Axis
    svg.select(".xAxis")
    .call(d3.axisBottom(x))
    // Add the Y Axis
    svg.select(".yAxis")
    .call(d3.axisLeft(y));

    //加入線段
    let newOil = svg.selectAll('.' + oiltype).data(oils).enter().append('g').attr('class', oiltype);
    newOil.append('path')
        .attr('class','line')
        .attr("fill", "none")
        .attr("d", function (d) {
            return line(d.values);
        })
        .style("stroke-width", "2")
        .style("stroke", function (d) {
            return color(d.name);
        })
        .style("opacity", 0)
        .transition().duration(750)
        .style("opacity", 1);
    if(oiltype=='oil92') {
        svg.selectAll(".oil92")
            .select('.line')
            .transition().duration(750)
            .style("opacity", 1);
        svg.selectAll(".oilDiesel")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil95")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil98")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
    } else if(oiltype=='oil95') {
        svg.selectAll(".oil95")
            .select('.line')
            .transition().duration(750)
            .style("opacity", 1);
        svg.selectAll(".oilDiesel")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil92")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil98")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
    } else if(oiltype=='oil98') {
        svg.selectAll(".oil98")
            .select('.line')
            .transition().duration(750)
            .style("opacity", 1);
        svg.selectAll(".oilDiesel")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil92")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil95")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
    } else {
        svg.selectAll(".oilDiesel")
            .select('.line')
            .transition().duration(750)
            .style("opacity", 1);
        svg.selectAll(".oil98")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil92")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
        svg.selectAll(".oil95")
            .select('.line')
            .transition().duration(500)
            .style("opacity", 0);
    }
    svg.selectAll('.legend')
        .select('.name2')
        .text(oilrect);
    
}

d3.csv(csvData, function (data) {
    color.domain(d3.keys(data[0]).filter(function (key) {
        return key !== "date";
    }));

    data.forEach(function (d) {
        d.date = parseDate(d.date);
    });

    //mapping資料
    let oils = color.domain().map(function (name) {
        return {
            name: name,
            values: data.map(function (d) {
                return {
                    date: d.date,
                    prices: +d[name]
                };
            })
        };
    });

    //座標軸設定
    x.domain(d3.extent(data, function (d) {
        return d.date;
    }));
    y.domain([
        d3.min(oils, function (c) {
            return d3.min(c.values, function (v) {
                return v.prices;
            });
        }),
        d3.max(oils, function (c) {
            return d3.max(c.values, function (v) {
                return v.prices;
            });
        })
    ]);

    //線段標籤設定、加入
    let legend = svg.selectAll('g')
        .data(oils)
        .enter()
        .append('g')
        .attr('class', 'legend');
    legend.append('rect')
        .attr('class', 'rect1')
        .attr('x', width - 170)
        .attr('y', height - 340)
        .attr('width', 10)
        .attr('height', 10)
        .attr('pointer-events', 'all')
        .style('fill', '#1E77B4')
        .on('mouseover', function () {
            d3.select(this)
                .attr('width', 12)
                .attr('height', 12);
            d3.selectAll('.line1')
                .style('stroke-width', '4')
        })
        .on('mouseout', function () {
            d3.select(this)
                .attr('width', 10)
                .attr('height', 10);
            d3.selectAll('.line1')
                .style('stroke-width', '2')
        });
    legend.append('rect')
        .attr('class', 'rect2')
        .attr('x', width - 170)
        .attr('y', height - 320)
        .attr('width', 10)
        .attr('height', 10)
        .attr('pointer-events', 'all')
        .style('fill', '#FF7F0F')
        .on('mouseover', function () {
            d3.select(this)
                .attr('width', 12)
                .attr('height', 12);
            d3.selectAll('.line2')
                .style('stroke-width', '4')
        })
        .on('mouseout', function () {
            d3.select(this)
                .attr('width', 10)
                .attr('height', 10);
            d3.selectAll('.line2')
                .style('stroke-width', '2')
        });
    legend.append('text')
        .attr("class", "name1")
        .attr("x", width - 150)
        .attr('y', height - 330)
        .attr("font-family", "Saira")
        .text("BRF原油期貨");
    legend.append('text')
        .attr("class", "name2")
        .attr("x", width - 150)
        .attr('y', height - 310)
        .attr("font-family", "Saira")
        .text("超級柴油");

    //右上標籤旁數字 
    legend.append('text')
        .attr("class", "mouse")
        .attr("x", width - 35)
        .attr('y', function (d, i) {
            return (i * 20) - 30.5;
        })
        .attr("font-family", "Saira")
        .style("font-weight", "bold")
        .style('fill', function (d) {
            return color(d.name);
        })
    
    //加入線段
    let oil = svg.selectAll(".oilDiesel")
        .data(oils)
        .enter().append("g")
        .attr("class", "oilDiesel");
    oil.append("path")
        .attr("class", "line")
        .attr("fill", "none")
        .attr("d", function (d) {
            return line(d.values);
        })
        .style("stroke-width", "2")
        .style("stroke", function (d) {
            return color(d.name);
        });
    // Add the X Axis
    svg.append("g")
    .attr("class", "xAxis")
    .attr("transform", "translate(0," + height + ")")
    .call(d3.axisBottom(x))

    // Add the Y Axis
    svg.append("g")
    .attr("class", "yAxis")
    .call(d3.axisLeft(y));

    //加上座標軸標籤
    svg.append("text")
    .attr("transform", "rotate(-90)")
    .attr("y", 6)
    .attr("dy", "1em")
    .style("text-anchor", "end")
    .attr("font-family", "Saira")
    .text("每公升新台幣");
    //設定滑鼠座標    
    let mouseG = svg.append("g")
        .attr("class", "mouse-over-effects");

    mouseG.append("path") // this is the black vertical line to follow mouse
        .attr("class", "mouse-line")
        .style("stroke", "black")
        .style("stroke-width", "1px")
        .style("opacity", "0");

    let lines = document.getElementsByClassName('line');

    let mousePerLine = mouseG.selectAll('.mouse-per-line')
        .data(oils)
        .enter()
        .append("g")
        .attr("class", "mouse-per-line");

    mousePerLine.append("circle")
        .attr("r", 7)
        .style("stroke", function (d) {
            return color(d.name);
        })
        .style("fill", "none")
        .style("stroke-width", "1px")
        .style("opacity", "0");

    mousePerLine.append("text")
        .attr("font-family", "Saira")
        .attr("transform", "translate(10,3)");

    mouseG.append('svg:rect') // append a rect to catch mouse movements on canvas
        .attr('class', 'mouseG')
        .attr('width', width) // can't catch mouse events on a g element
        .attr('height', height)
        .attr('fill', 'none')
        .attr('pointer-events', 'all')
        .on('mouseout', function () { // on mouse out hide line, circles and text
            d3.select(".mouse-line")
                .style("opacity", "0");
            d3.selectAll(".mouse-per-line circle")
                .style("opacity", "0");
            d3.selectAll(".mouse-per-line text")
                .style("opacity", "0");
            d3.selectAll(".mouse")
                .style("opacity", "0");
        })
        .on('mouseover', function () { // on mouse in show line, circles and text
            d3.select(".mouse-line")
                .style("opacity", "1");
            d3.selectAll(".mouse-per-line circle")
                .style("opacity", "1");
            d3.selectAll(".mouse-per-line text")
                .style("opacity", "1");
            d3.selectAll(".mouse")
                .style("opacity", "1");
        })
        .on('mousemove', function () { // mouse moving over canvas
            let mouse = d3.mouse(this);
            d3.select(".mouse-line")
                .attr("d", function () {
                    let d = "M" + mouse[0] + "," + height;
                    d += " " + mouse[0] + "," + 0;
                    return d;
                });

            d3.selectAll(".mouse-per-line")
                .attr("transform", function (d, i) {
                    console.log(width / mouse[0])
                    let xDate = x.invert(mouse[0]),
                        bisect = d3.bisector(function (d) { return d.date; }).right;
                    idx = bisect(d.values, xDate);

                    let beginning = 0,
                        end = lines[i].getTotalLength(),
                        target = null;

                    while (true) {
                        target = Math.floor((beginning + end) / 2);
                        pos = lines[i].getPointAtLength(target);
                        if ((target === end || target === beginning) && pos.x !== mouse[0]) {
                            break;
                        }
                        if (pos.x > mouse[0]) end = target;
                        else if (pos.x < mouse[0]) beginning = target;
                        else break; //position found
                    }

                    d3.select(this).select('text')
                        .text(y.invert(pos.y).toFixed(2));

                    return "translate(" + mouse[0] + "," + pos.y + ")";
                });

            //右上座標設定    
            d3.selectAll('.mouse')
                .text(function (d, i) {
                    console.log(width / mouse[0])
                    let xDate = x.invert(mouse[0]),
                        bisect = d3.bisector(function (d) { return d.date; }).right;
                    idx = bisect(d.values, xDate);

                    let beginning = 0,
                        end = lines[i].getTotalLength(),
                        target = null;

                    while (true) {
                        target = Math.floor((beginning + end) / 2);
                        pos = lines[i].getPointAtLength(target);
                        if ((target === end || target === beginning) && pos.x !== mouse[0]) {
                            break;
                        }
                        if (pos.x > mouse[0]) end = target;
                        else if (pos.x < mouse[0]) beginning = target;
                        else break; //position found
                    }

                    return y.invert(pos.y).toFixed(2);

                })
        });
 
});