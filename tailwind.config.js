/** @type {import('tailwindcss').Config} */
module.exports = {
  theme: {
    extend: {
      borderWidth: {
        '10': '10px', // Example for a 10px border
        '12': '12px', // Example for a 12px border
        '16': '16px', // Example for a 16px border
        '20': '20px', // Example for a 20px border
        // Add more as needed
      },
      maxWidth: {
        'screen-2xl': '1536px', // You can define a larger max-width
        'custom': '2000px',     // Define a custom width as needed
      },
    },
  },
  content: ['./src/main/resources/templates/**/*.html',
    './src/main/resources/static/js/**/*.js',
    './src/main/resources/static/css/**/*.css',],
  theme: {
    extend: {},
  },
  plugins: [],
  darkMode:"selector"
}