<template>
  <div>
    <h1>{{ message }}</h1>
    <button @click="fetchMessage">Fetch Message from Backend</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: 'Hello Vue.js!'
    };
  },
  methods: {
    async fetchMessage() {
      try {
        const response = await fetch('/api/hello');
        const data = await response.text();
        this.message = data;
      } catch (error) {
        console.error('Error fetching message:', error);
        this.message = 'Failed to fetch message from backend.';
      }
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
