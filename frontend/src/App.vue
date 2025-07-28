<template>
  <h1>Hello, Vue!</h1>
  <button @click="handleClick">Click Me!</button>
  <p v-if="buttonClicked">Button was clicked!</p>
  <p v-if="response">Backend response: {{ response }}</p>
</template>

<script setup lang="ts">
// You can add script logic here if needed
import {ref} from 'vue'
import log from 'loglevel'

const buttonClicked = ref(false)
const response = ref('')

const handleClick = async () => {
  buttonClicked.value = true

  try {
    const res = await fetch('http://localhost:8082/api/button-click', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({timestamp: new Date().toISOString()})
    })

    const data = await res.text()
    response.value = data
    log.info('Backend notified successfully')
  } catch (error) {
    log.error('Failed to notify backend:', error)
    response.value = 'Error contacting backend'
  }
}
</script>

<style scoped>
h1 {
  color: #42b983;
}
</style>