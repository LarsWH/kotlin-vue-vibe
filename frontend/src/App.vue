<template>
  <h1>Hello, Vue!</h1>
  <button @click="handleClick" :disabled="loading">Click Me!</button>
  <button @click="checkHealth" :disabled="healthLoading">Check Health</button>

  <p v-if="loading">Loading...</p>
  <p v-if="healthLoading">Checking health...</p>

  <p v-if="buttonClicked">Button was clicked!</p>
  <p v-if="response">Backend response: {{ response }}</p>
  <p v-if="healthStatus">Health status: {{ healthStatus }}</p>
  <p v-if="error" style="color: red;">Error: {{ error }}</p>
</template>

<script setup lang="ts">
// You can add script logic here if needed
import {ref} from 'vue'
import log from 'loglevel'

const buttonClicked = ref(false)
const response = ref('')
const healthStatus = ref('')
const error = ref('')
const loading = ref(false)
const healthLoading = ref(false)

const handleClick = async () => {
  console.log('handleClick function called')
  buttonClicked.value = true
  error.value = ''
  loading.value = true

  try {
    const res = await fetch('http://localhost:8082/api/button-click', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({timestamp: new Date().toISOString()})
    })

    if (!res.ok) {
      throw new Error(`Error ${res.status}: ${res.statusText}`)
    }

    const data = await res.text()
    response.value = data
    log.info('Backend notified successfully')
  } catch (err) {
    log.error('Failed to notify backend:', err)
    response.value = 'Error contacting backend'
  } finally {
    loading.value = false
  }
}

const checkHealth = async () => {
  console.log('checkHealth function called')
  healthStatus.value = ''
  error.value = ''
  healthLoading.value = true

  try {
    const res = await fetch('http://localhost:8082/health')

    if (!res.ok) {
      throw new Error(`Error ${res.status}: ${res.statusText}`)
    }

    const data = await res.json()
    healthStatus.value = `Status: ${data.status}${data.error ? `, Error: ${data.error}` : ''}`
    log.info('Health check successful:', data)
  } catch (err) {
    log.error('Health check failed:', err)
    error.value = 'Health check failed'
  } finally {
    healthLoading.value = false
  }
}


</script>

<style scoped>
h1 {
  color: #42b983;
}

button {
  margin-right: 10px;
}

</style>