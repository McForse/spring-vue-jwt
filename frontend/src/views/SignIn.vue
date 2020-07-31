<template>
	<v-card>
		<v-card-title>Login</v-card-title>

		<v-card-text>
			<v-form
				ref="form"
				v-model="valid"
			>
				<v-text-field
					v-model="username"
					:counter="16"
					:rules="nameRules"
					:error-messages="usernameError"
					label="Username"
					prepend-icon="mdi-account"
					required
				/>

				<v-text-field
					v-model="password"
					:rules="passwordRules"
					:error-messages="passwordError"
					label="Password"
					type="password"
					prepend-icon="mdi-lock"
					required
				/>
			</v-form>

			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn
					color="primary"
					:disabled="!valid"
					:loading="loading"
					@click="login"
				>Login</v-btn>
			</v-card-actions>
		</v-card-text>
	</v-card>
</template>

<script>
	import AuthApi from "@/services/api/Auth"

	export default {
		name: 'SignIn',
		data: () => ({
			username: '',
			password: '',
			valid: true,
			loading: false,
			usernameError: '',
			passwordError: '',
			nameRules: [
				v => !!v || 'Username is required',
				v => (v && v.length <= 16) || 'Username must be less than 16 characters'
			],
			passwordRules: [
				v => !!v || 'Password is required'
			],
		}),
		methods: {
			login() {
				this.loading = true
				AuthApi.login(this.username, this.password)
					.then(response => {
						console.log(response);
						this.$store.dispatch('login', {
							'roles': response.data.roles,
							'username': response.data.username
						})
						this.$router.push('/home')
					}, error => {
						let e = error.response.data.message
						console.log(e)

						if (e === 'User not found!') {
							this.usernameError = e
						} else if (e === 'Invalid credentials') {
							this.passwordError = e
						} else {
							this.notify(e, 'error')
						}
					})
					.catch(e => {
						console.log(e)
						this.notify('Server error. Please, try later', 'error')
					})
					.finally(() => this.loading = false)
			},
		},
		watch: {
			username() {
				this.usernameError = ''
			},
			password() {
				this.passwordError = ''
			}
		}
	}
</script>

<style>
</style>
