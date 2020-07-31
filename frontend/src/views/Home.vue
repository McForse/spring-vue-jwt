<template>
	<v-card>
		<v-card-title>Spring Boot + Vue.js + Jwt</v-card-title>

		<v-card-text>
			This is the demo web-application using Spring Boot and Vue.js for frontend
		</v-card-text>

		<v-card-actions
			v-if="!this.$store.getters.isAuthenticated"
		>
			<v-btn
				text
				to="/login"
			>Login and start
			</v-btn>
		</v-card-actions>
	</v-card>
</template>

<script>
	export default {
		name: 'Home',
	}
</script>

<style>
</style>
