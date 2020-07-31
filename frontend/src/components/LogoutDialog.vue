<template>
	<v-dialog
		v-model="dialog"
		max-width="290"
	>
		<v-card>
			<v-card-title class="headline">Logout</v-card-title>

			<v-card-text>
				Are you sure you want to log out of your account?
			</v-card-text>

			<v-card-actions>
				<v-spacer/>

				<v-btn
					text
					color="green darken-1"
					@click="logout"
				>
					Yes
				</v-btn>

				<v-btn
					text
					color="green darken-1"
					@click="dialog = false"
				>
					No
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
	import AuthApi from '@/services/api/Auth'
	import {EventBus} from '@/main'

	export default {
		name: 'LogoutDialog',
		data: () => ({
			dialog: false,
		}),
		created() {
			EventBus.$on('showLogoutDialog', () => {
				this.dialog = true
			})
		},
		methods: {
			logout() {
				this.dialog = false
				AuthApi.logout()
					.then(response => {
						console.log(response.statusText)
						this.$store.dispatch('logout')
						this.$router.push('/').catch(() => {
						})
						this.notify('You have successfully logged out', 'success')
					}, error => {
						console.log(error)
						this.notify('Server error. Please, try later', 'error')
					})
			},
		},
	}
</script>

<style>
</style>