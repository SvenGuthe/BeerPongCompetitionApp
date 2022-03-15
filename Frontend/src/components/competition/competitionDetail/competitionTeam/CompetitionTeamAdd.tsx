import React, { useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { tUserIDAndGamerTag } from "../../../../types/authentication";
import { tTeamAndUser } from "../../../../types/team";
import { removeDuplicates } from "../../../../utility/arrayFunctions";

const CompetitionTeamAdd: React.FC<{
    teams: tTeamAndUser[]
}> = (props) => {

    const [selectedTeam, setSelectedTeam] = useState<tTeamAndUser | null>(null);
    const [selectedUsers, setSelectedUsers] = useState<tUserIDAndGamerTag[]>([]);

    const [competitionTeamName, setCompetitionTeamName] = useState<string>("");
    const [competitionTeamPassword, setCompetitionTeamPassword] = useState<string>("");

    const teams = props.teams;

    const onSelectChangeHandler = (event: React.FormEvent<HTMLSelectElement>) => {
        const value = event.currentTarget.value;

        const chosenTeam = teams.find(team => team.team.teamName === value);

        if (chosenTeam) {
            setSelectedTeam(chosenTeam);
        } else {
            setSelectedTeam(null)
        }
    }

    const onMultiSelectChangeHandler = (event: React.FormEvent<HTMLSelectElement>) => {
        const value = event.currentTarget.selectedOptions;
        const possibleUsersOption = removeDuplicates(teams.flatMap(team => team.users));

        let selectedValues: tUserIDAndGamerTag[] = [];
        for (let i = 0; i < value.length; i++) {
            possibleUsersOption?.filter(user => user.gamerTag === value.item(i)?.value).forEach(user => {
                selectedValues.push(user);
            });
        }
        setSelectedUsers(selectedValues);
    }

    const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();

        setSelectedTeam(null);
        setSelectedUsers([]);
        setCompetitionTeamName("");
        setCompetitionTeamPassword("");
    }

    let possibleTeams: string[] = [];

    if (selectedTeam) {
        possibleTeams = selectedTeam.users.map(singleUser => singleUser.gamerTag);
    } else {
        const possibleTeamsOption = removeDuplicates(teams.flatMap(team => team.users))?.map(team => team.gamerTag);
        possibleTeams = possibleTeamsOption ? possibleTeamsOption : [];
    }

    const defaultValue = {
        id: -1,
        teamName: "Ohne Hauptteam"
    }
    const selectTeamDefaults = [defaultValue].concat(teams.map(team => team.team));

    const output = <Container style={{ padding: "0px", margin: "0px" }}>
        <Form>
            <Row>
                <Form.Group as={Col} sm={2}>
                    <Form.Label htmlFor="competitionTeamName">Turnier Team Name</Form.Label>
                    <Form.Control id="competitionTeamName" type="text" placeholder="Enter Team Name" value={competitionTeamName} onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCompetitionTeamName(e.currentTarget.value)} />
                </Form.Group>
                <Form.Group as={Col} sm={2}>
                    <Form.Label htmlFor="competitionTeamPassword">Passwort</Form.Label>
                    <Form.Control id="competitionTeamPassword" type="password" placeholder="Enter Password" value={competitionTeamPassword} onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCompetitionTeamPassword(e.currentTarget.value)} />
                </Form.Group>
                <Form.Group as={Col} sm={4}>
                    <Form.Label htmlFor="competitionMainTeam">Turnier Team Name</Form.Label>
                    <Form.Select id="competitionMainTeam" onChange={onSelectChangeHandler} value={selectedTeam?.team.teamName ? selectedTeam?.team.teamName : defaultValue.teamName}>
                        {selectTeamDefaults.map(selectTeamDefault => <option key={selectTeamDefault.id}>{selectTeamDefault.teamName}</option>)}
                    </Form.Select>
                </Form.Group>
                <Form.Group as={Col} sm={2}>
                    <Form.Label htmlFor="competitionTeamUser">Nutzer</Form.Label>
                    <Form.Select id="competitionTeamUser" onChange={onMultiSelectChangeHandler} multiple value={selectedUsers.map(user => user.gamerTag)}>
                        {possibleTeams.map(possibleTeam => <option key={possibleTeam}>{possibleTeam}</option>)}
                    </Form.Select>
                </Form.Group>
                <Col sm={2} style={{ textAlign: "right" }}>
                    <Button variant="secondary" type="submit" size="sm" onClick={onAddHandler} style={{ width: '100px' }}>
                        Add
                    </Button>
                </Col>
            </Row>
        </Form>
    </Container >

    return output;

}

export default CompetitionTeamAdd;