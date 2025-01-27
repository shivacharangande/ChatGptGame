const gameCanvas = document.getElementById("gameCanvas");
const scoreDisplay = document.getElementById("score");
const timerDisplay = document.getElementById("timer");

let score = 0;
let timeLeft = 30;
let gameInterval;
let targetInterval;

// Function to generate a random position within the game canvas
function getRandomPosition() {
    const x = Math.random() * (gameCanvas.offsetWidth - 50);
    const y = Math.random() * (gameCanvas.offsetHeight - 50);
    return { x, y };
}

// Function to create and display a target
function spawnTarget() {
    const target = document.createElement("div");
    target.classList.add("target");
    const { x, y } = getRandomPosition();
    target.style.left = `${x}px`;
    target.style.top = `${y}px`;

    target.addEventListener("click", () => {
        score++;
        scoreDisplay.textContent = `Score: ${score}`;
        gameCanvas.removeChild(target);
    });

    gameCanvas.appendChild(target);

    // Remove the target after 2 seconds if not clicked
    setTimeout(() => {
        if (gameCanvas.contains(target)) {
            gameCanvas.removeChild(target);
        }
    }, 2000);
}

// Function to update the timer
function updateTimer() {
    timeLeft--;
    timerDisplay.textContent = `Time: ${timeLeft}`;
    if (timeLeft <= 0) {
        clearInterval(gameInterval);
        clearInterval(targetInterval);
        alert(`Game Over! Your score is ${score}`);
    }
}

// Start the game
function startGame() {
    score = 0;
    timeLeft = 30;
    scoreDisplay.textContent = `Score: ${score}`;
    timerDisplay.textContent = `Time: ${timeLeft}`;

    gameInterval = setInterval(updateTimer, 1000);
    targetInterval = setInterval(spawnTarget, 800); // Spawn a new target every 800ms
}

// Start the game on load
startGame();
