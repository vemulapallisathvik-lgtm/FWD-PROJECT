document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("calorieForm");

    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            let age = parseInt(document.getElementById("age").value);
            let gender = document.getElementById("gender").value;
            let height = parseFloat(document.getElementById("height").value);
            let weight = parseFloat(document.getElementById("weight").value);
            let activity = parseFloat(document.getElementById("activity").value);

            let bmr;
            if (gender === "male") {
                bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
            } else {
                bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
            }

            let maintenanceCalories = Math.round(bmr * activity);
            let cuttingCalories = Math.round(maintenanceCalories * 0.8);
            let bulkingCalories = Math.round(maintenanceCalories * 1.2);

            let protein = Math.round((maintenanceCalories * 0.30) / 4);
            let carbs = Math.round((maintenanceCalories * 0.40) / 4);
            let fats = Math.round((maintenanceCalories * 0.30) / 9);

            localStorage.setItem("bmr", bmr);
            localStorage.setItem("calories", maintenanceCalories);
            localStorage.setItem("cutting", cuttingCalories);
            localStorage.setItem("bulking", bulkingCalories);
            localStorage.setItem("protein", protein);
            localStorage.setItem("carbs", carbs);
            localStorage.setItem("fats", fats);

            // Redirect to loading page
            window.location.href = "loading.html";
        });
    }

    if (document.getElementById("calories")) {
        document.getElementById("bmr").innerText = localStorage.getItem("bmr");
        document.getElementById("calories").innerText = localStorage.getItem("calories");
        document.getElementById("cutting").innerText = localStorage.getItem("cutting");
        document.getElementById("bulking").innerText = localStorage.getItem("bulking");
        document.getElementById("protein").innerText = localStorage.getItem("protein");
        document.getElementById("carbs").innerText = localStorage.getItem("carbs");
        document.getElementById("fats").innerText = localStorage.getItem("fats");

        const ctx = document.getElementById('macroChart').getContext('2d');
        new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ['Protein', 'Carbs', 'Fats'],
                datasets: [{
                    data: [
                        parseInt(localStorage.getItem("protein")),
                        parseInt(localStorage.getItem("carbs")),
                        parseInt(localStorage.getItem("fats"))
                    ],
                    backgroundColor: ['#ff4d4d','#4da6ff','#ffd11a']
                }]
            },
            options: {
                responsive: true,
                plugins: { legend: { position: 'bottom' } }
            }
        });
    }
});

function goBack() {
    window.location.href = "index.html";
}