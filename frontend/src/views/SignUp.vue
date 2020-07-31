<template>
	<v-card>
		<v-card-title>Register</v-card-title>

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

				<v-text-field
					v-model="confirmPassword"
					:rules="passwordRules"
					:error-messages="confirmPasswordError"
					label="Confirm Password"
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
					@click="register"
				>Register
				</v-btn>
			</v-card-actions>
		</v-card-text>
	</v-card>
</template>

<script>
	import AuthApi from '@/services/api/Auth'

	export default {
		name: 'SignUp',
		data() {
			return {
				username: '',
				password: '',
				confirmPassword: '',
				valid: true,
				loading: false,
				usernameError: '',
				passwordError: '',
				confirmPasswordError: '',
				nameRules: [
					v => !!v || 'Username is required',
					v => (v && v.length <= 16) || 'Username must be less than 16 characters',
				],
				passwordRules: [
					v => !!v || 'Password is required',
				],
			}
		},
		methods: {
			register() {
				if (this.confirmPassword !== this.password) {
					this.confirmPasswordError = 'Passwords are not match'
				} else {
					this.loading = true
					AuthApi.register(this.username, this.password)
						.then(response => {
							console.log(response)
							this.notify('You have been successfully registered! Now you can login with your credentials', 'success')
							this.$router.push('/login')
						}, error => {
							let e = error.response.data.message
							console.log(e)

							if (e === 'User already exists!') {
								this.usernameError = e
							} else {
								this.notify('Server error. Please, try later', 'error')
							}
						})
						.catch(error => {
							console.log(error)
							this.notify('Server error. Please, try later', 'error')
						})
						.finally(() => this.loading = false)
				}
			},
		},
		watch: {
			username() {
				this.usernameError = ''
			},
			password() {
				this.passwordError = ''
			},
			confirmPassword() {
				this.confirmPasswordError = ''
			},
		},
	}
</script>

<style>
</style>
