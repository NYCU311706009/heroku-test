// set the dimensions and margins of the graph
var width = 300
    height = 300
    margin = 25;

// The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
var radius = Math.min(width, height) / 2 - margin;

// append the svg object to the div called 'my_dataviz'
var svg = d3.select("#my_dataviz")
  .append("svg")
    .attr("width", width)
    .attr("height", height)
  .append("g")
    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

// Create dummy data
var data = {remain: 25, consumed: 75};

// set the color scale
var color = d3.scaleOrdinal()
  .domain(data)
  .range(["#FFFFFF", "#724DFF"]);

// Compute the position of each group on the pie:
var pie = d3.pie()
  .value(function(d) {return d.value; });
var data_ready = pie(d3.entries(data));

// Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
svg
  .selectAll('whatever')
  .data(data_ready)
  .enter()
  .append('path')
  .attr('d', d3.arc()
    .innerRadius(110)         // This is the size of the donut hole
    .outerRadius(radius)
  )
  .attr('fill', function(d){ return(color(d.data.key)) })
  .style("opacity", 0.7);

// Add text
const remain = "75"
const total = "100"
const dateline = "2021/08/31"

svg.append("text")
.attr('class', 'numNow')
.attr("text-anchor", "middle")
.attr("font-size", "45px")
.attr("font-weight", "bolder")
.style("fill", "#724DFF")
.text(remain + "公升");

svg.append("text")
.attr('class', 'numTotal')
.attr("text-anchor", "middle")
.attr("font-size", "25px")
.attr("transform", "translate(20,30)")
.text("/" + total + "公升");

svg.append("text")
.attr('class', 'numDateline')
.attr("text-anchor", "middle")
.attr("font-size", "15px")
.attr("transform", "translate(0,70)")
.text("到期日:" + dateline);