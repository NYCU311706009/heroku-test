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

let parseDate = d3.timeParse("%Y/%m/%d");

let color = d3.scaleOrdinal(d3.schemeCategory10);

d3.csv("https://raw.githubusercontent.com/k22949706/Project-Oil/master/2018_19_20%20BRF.csv", function (data) {

    color.domain(d3.keys(data[0]).filter(function (key) {
        return key !== "date";
    }));

    data.forEach(function (d) {
        d.date = parseDate(d.date);
        d.fPrice = +d.fPrice;
        d.sPrice = +d.sPrice;
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
        .attr('x', width - 100)
        .attr('y', height - 340)
        .attr('width', 10)
        .attr('height', 10)
        .attr('pointer-events', 'all')
        .style('fill', 'steelblue')
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
        .attr('x', width - 100)
        .attr('y', height - 320)
        .attr('width', 10)
        .attr('height', 10)
        .attr('pointer-events', 'all')
        .style('fill', 'orange')
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
        .attr("x", width - 88)
        .attr('y', height - 330)
        .attr("font-family", "Saira")
        .text("BRF");
    legend.append('text')
        .attr("class", "name2")
        .attr("x", width - 88)
        .attr('y', height - 310)
        .attr("font-family", "Saira")
        .text("Gas95");
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

    let line1 = d3
        .line()
        .curve(d3.curveBasis)
        .x(function (d) {
            return x(d.date);
        })
        .y(function (d) {
            return y(d.fPrice);
        });

    let line2 = d3
        .line()
        .curve(d3.curveBasis)
        .x(function (d) {
            return x(d.date);
        })
        .y(function (d) {
            return y(d.sPrice);
        });

    //加入線段
    let oil = svg.selectAll(".oil")
        .data(oils)
        .enter().append("g")
        .attr("class", "oil");
    oil.append("path")
        .attr("class", "line")
        .attr("d", function (d) {
            return line(d.values);
        })
        .style("stroke-width", "2")
        .style("stroke", function (d) {
            return color(d.name);
        });

    oil.append("path")
        .data([data])
        .attr("class", "line1")
        .attr("fill", "none")
        .attr("d", line1)
        .style("stroke-width", "2")
        .style("stroke", "steelblue");

    oil.append("path")
        .data([data])
        .attr("class", "line2")
        .attr("fill", "none")
        .attr("d", line2)
        .style("stroke-width", "2")
        .style("stroke", "orange");

    // Add the X Axis
    svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x))

    // Add the Y Axis
    svg.append("g")
        .call(d3.axisLeft(y));

    //加上座標軸標籤
    svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", "1em")
        .style("text-anchor", "end")
        .attr("font-family", "Saira")
        .text("Price Change/L");

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
    function reDrawLine() {
        svg.selectAll(".line1")
            .attr("fill", fillOrNot);
    }
    let open = false;
    function lookArea() {
        if (open === false) {
            open = true;
            fillOrNot = "steelblue";
            reDrawLine();
        } else {
            open = false;
            fillOrNot = "none";
            reDrawLine();
        }
    }

    document.getElementById("click").onclick = lookArea;        //讓按鈕讀取方法

});