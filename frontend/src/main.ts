import { createApp } from 'vue'
import App from './App.vue'
import log from 'loglevel'

log.setLevel('info')
log.info('Frontend started')

createApp(App).mount('#app')