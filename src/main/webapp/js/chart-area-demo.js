// Set new default font family and font color to mimic Bootstrap's default styling
var chart = new Chart('myAreaChart', {
  type: 'horizontalBar',
  data: {
    labels: ['A', 'B', 'C'],
    datasets: [
      {
        data: [10, 20, 30],
        backgroundColor: '#af90ca',
        label: 'Before'
      },
      {
        data: [50, 30, 40],
        backgroundColor: '#c46998',
        label: 'After'
      }
    ]
  },
  options: {
    scales: {
      xAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  }
});