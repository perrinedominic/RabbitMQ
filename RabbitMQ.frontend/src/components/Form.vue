<template>
  <div>
    <button @click="showModal = true" class="btn btn-primary">
      Send Notification
    </button>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <h2>Send Notification</h2>

        <input v-model="form.title" placeholder-="Title">
        <textarea v-model="form.message" placeholder="Message"></textarea>

        <select v-model="form.priority">
          <option value="low">Low</option>
          <option value="medium">Medium</option>
          <option value="high">High</option>
        </select>

        <button @click="sendNotification">Send</button>
        <button @click="showModal = false">Cancel</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const showModal = ref(false)

const form = ref({
      title: '',
      message: '',
      priority: 'medium',
      status: 'pending'
    })

const sendNotification = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/notifications/send', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form.value)
    })

    if (response.ok) {
      alert('Notification sent!')
      form.value = { title: '', message: '', priority: 'medium', status: 'pending' }
      showModal.value = false
    }
  } catch (error) {
    console.error('Error:', error)
  }
}
</script>

<style scoped>
.btn {
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.btn:hover {
  background-color: #0056b3;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 30px;
  border-radius: 8px;
  max-width: 500px;
  width: 90%;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.modal-header h2 {
  margin: 0;
}

.close-btn {
  background: none;
  border: none;
  font-size: 28px;
  cursor: pointer;
  color: #999;
}

.close-btn:hover {
  color: #000;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-family: inherit;
  box-sizing: border-box;
}

.form-group textarea {
  resize: vertical;
  min-height: 80px;
}

.modal-footer {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
  margin-top: 20px;
}

.modal-footer button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.modal-footer button[type="submit"] {
  background-color: #007bff;
  color: white;
}

.modal-footer button[type="submit"]:hover:not(:disabled) {
  background-color: #0056b3;
}

.modal-footer button[type="submit"]:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.modal-footer button[type="button"] {
  background-color: #6c757d;
  color: white;
}

.modal-footer button[type="button"]:hover {
  background-color: #5a6268;
}

.alert {
  margin-top: 15px;
  padding: 10px;
  border-radius: 4px;
}

.alert.success {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

.alert.error {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}
</style>